# Image Processing (GUI)

## Description
Java program for processing [PPM](https://en.wikipedia.org/wiki/Netpbm_format#PPM_example) and [YUV](https://en.wikipedia.org/wiki/YUV) images. It can perform the following actions:
1. Greyscale
2. Increase Size
3. Decrease Size
4. Rotate Clockwise
5. Equalize Histogram
6. Stacking Algorithm

## Stacking Algorithm
<p style="text-align: justify;">The stacking process is applied to images that take up to several seconds. Usually there are evening shots (landscapes, monuments or skyscrapers). Prolonged exposure of an image to light induces noise, which blurs the end result. In order to overcome the problem of the imported noise, the stacking technique is applied, which is summarized below:</p>

<p style="text-align: justify;">Several images of the same subject are taken using the same extended exposure duration. These images show a single image based on the following algorithm: each of the RGB values ​​of any pixel of the final image is derived from the average of the RGB values ​​of the corresponding pixel of all the images involved.</p>

For this action you need to select a directory containing several images with the same subject.
