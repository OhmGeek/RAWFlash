# RAWFlash
A RAW Photo Editor, designed with UX in mind. Designed as part of my Individual Project in Year 3 at Durham University.

## Goals:
- Simple to use software, with key features represented visually (rather than within lots of windows).
- All tools should be available within about 5 clicks
- Quality isn't lost when consistently editing photos (as we use the RAW photo as a basis)

## Dependencies:
### Front-end:
- ReactJS
- Grommet UI

Eventually, we will use Redux to manage the stage (i.e. the current image, the current operations, etc).
### Back-end:
I'm not fully sure yet. I'm  thinking of using something like Java/Scala (or maybe Go) to process images, as they are pretty quick. Alternatively, Python/Flask. 

Basically, this will need to deal with basic application state changes, such as loading images, as well as processing images themselves.

This ultimately needs to scale (as part of the project). Failing that, we can run it as an electron app (if we decide to make it easier for myself).

We will need some way of storing the transformations, perhaps using a NoSQL database? Needs to persist if an application crashes for example.

## Proposed Features:
### Photo Editing:
- Exposure adjustment
- Contrast adjustment
- Colour adjustment
- Graduated filtering
- Spot healing
- Noise reduction
- Haze removal
- Cropping
- Rotating

### Image Management:
- Uploading Images
- Image Selection
- Image Exporting (as JPG, PNG)

## More info needed
- The application itself should read in DNG RAW files, and allow exporting to a variety of different files (PNG, JPEG).
- How to use Redux for state changes
- React actions
- How do I carry out above operations?
