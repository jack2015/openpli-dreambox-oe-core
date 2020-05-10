# Now we could change this python2 to 3 with just one file!

do_compile_append() {
    python2 -O -m compileall ${S}
}
