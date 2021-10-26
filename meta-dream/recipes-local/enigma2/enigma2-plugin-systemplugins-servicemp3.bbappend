GST_GOOD_RDEPS:append:arm = "\
	gstreamer${GST_VERSION}-plugins-good-vpx \
	"

GST_BAD_RDEPS:append:arm = "\
	gstreamer${GST_VERSION}-plugins-bad-mpegpsmux \
	gstreamer${GST_VERSION}-plugins-bad-mpegtsmux \
	"
