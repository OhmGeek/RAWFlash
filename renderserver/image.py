import rawpy
import imageio
import numpy as np


class Image(object):
    def __init__(self, path):
        self.width = None
        self.height = None
        self.raw_width = None
        self.raw_height = None
        self.pixel_aspect = None
        self.flip = None
        self.camera_wb = None
        self.image_values = None
        with rawpy.imread(path) as raw:
            self.image_values = raw.postprocess()
            self.camera_wb = raw.camera_whitebalance

            self.raw_height = raw.sizes.raw_height
            self.raw_width = raw.sizes.raw_width

            self.height = raw.sizes.height
            self.width = raw.sizes.width

            self.pixel_aspect = raw.sizes.pixel_aspect

            self.flip = raw.sizes.flip

    def display_data(self):
        print("Image Matrix:", str(self.image_values))
        print("White Balance:", str(self.camera_wb))
        print("RAW height:", str(self.raw_height))
        print("RAW width:", str(self.raw_width))
        print("Img height:", str(self.height))
        print("Img width:", str(self.width))
        print("Pixel aspect", str(self.pixel_aspect))
        print("Flip:", str(self.flip))

    def write_output(self):
        imageio.imwrite("out.tiff", self.image_values)
