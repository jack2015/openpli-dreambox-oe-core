FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


SRC_URI += " \
            file://04-default-is-optimized.patch \
            file://99-ignore-optimization-flag.patch \
            file://no-ldconfig.patch \
            file://setuptweaks-2.patch \
            file://pgettext.patch \
            file://get-rid-of-register-keyword.patch \
"

EXTRA_OECONF += " \
    ac_cv_file__dev_ptmx=yes \
    ac_cv_file__dev_ptc=no \
    ac_cv_no_strict_aliasing_ok=yes \
    ac_cv_pthread=yes \
    ac_cv_cxx_thread=yes \
    ac_cv_sizeof_off_t=8 \
"

python(){
    import collections, json

    filename = os.path.join(d.getVar('THISDIR'), 'python', 'python2-manifest.json')
    # This python changes the datastore based on the contents of a file, so mark
    # that dependency.
    bb.parse.mark_dependency(d, filename)

    with open(filename) as manifest_file:
        manifest_str =  manifest_file.read()
        json_start = manifest_str.find('# EOC') + 6
        manifest_file.seek(json_start)
        manifest_str = manifest_file.read()
        python_manifest = json.loads(manifest_str, object_pairs_hook=collections.OrderedDict)

    include_pycs = d.getVar('INCLUDE_PYCS')

    packages = d.getVar('PACKAGES').split()
    pn = d.getVar('PN')

    newpackages=[]

    for key in python_manifest:
        pypackage= pn + '-' + key

        if pypackage not in packages:
            # We need to prepend, otherwise python-misc gets everything
            # so we use a new variable
            newpackages.append(pypackage)

        # "Build" python's manifest FILES, RDEPENDS and SUMMARY
        d.setVar('FILES:' + pypackage, '')
        for value in python_manifest[key]['files']:
            d.appendVar('FILES:' + pypackage, ' ' + value)
            if include_pycs == '1':
                if value.endswith('.py'):
                    d.appendVar('FILES:' + pypackage, ' ' + value + 'o')

        d.setVar('RDEPENDS:' + pypackage, '')
        for value in python_manifest[key]['rdepends']:
            # Make it work with or without $PN
            if '${PN}' in value:
                value=value.split('-')[1]
            d.appendVar('RDEPENDS:' + pypackage, ' ' + pn + '-' + value)
        d.setVar('SUMMARY:' + pypackage, python_manifest[key]['summary'])

    # We need to ensure staticdev packages match for files first so we sort in reverse
    newpackages.sort(reverse=True)
    # Prepending so to avoid python-misc getting everything
    packages = newpackages + packages
    d.setVar('PACKAGES', ' '.join(packages))
    d.setVar('ALLOW_EMPTY:${PN}-modules', '1')
}

do_install:append(){
    python -m py_compile ${D}/${libdir}/python${PYTHON_MAJMIN}/sitecustomize.py
}

python populate_packages:prepend() {
    import os

    dest_path = d.getVar('D')
    bindir_path = d.getVar('bindir')
    libdir_path = d.getVar('libdir')
    includedir_path = d.getVar('includedir')
    datadir_path = d.getVar('datadir')

    def isFolder(value):
        if os.path.isdir(dest_path + '/' + value.replace('${bindir}', bindir_path).replace('${libdir}', libdir_path).replace('${includedir}', includedir_path).replace('${datadir}', datadir_path)):
            return True
        else:
            return False

    packages = d.getVar('PACKAGES').split()
    newpackages = []
    for p in packages:
        if p in ('python-dev', 'python-dbg', 'python-man', 'python-tests'):
            newpackages.append(p)
            continue
        # prepend -src package to move test directories to the -src package
        newpackages.append(p + '-src')
        newpackages.append(p)
        newfiles = []
        files = d.getVar('FILES:' + p).split()
        for file in files:
            if file.endswith('.py'):
                d.appendVar('FILES:' + p + '-src', ' ' + file)
            elif isFolder(file):
                newfiles.append(file + '/*.pyo')
                newfiles.append(file + '/*/*.pyo')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*.py')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*/*.py')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*/*/*.py')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/test/*')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/tests/*')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*.exe')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*/*.exe')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*/*/*.exe')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*.whl')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*/*.whl')
                d.appendVar('FILES:' + p + '-src', ' ' + file + '/*/*/*.whl')
            else:
                newfiles.append(file)
        if p != 'python-misc':
            d.setVar('FILES:' + p, ' '.join(newfiles))
        d.setVar('RDEPENDS:' + p + '-src', p)
        d.setVar('SUMMARY:' + p + '-src', p + ' (source)')

    d.setVar('PACKAGES', ' '.join(newpackages))
}
