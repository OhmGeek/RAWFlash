# RAWFlash
A RAW Photo Editor, designed with UX in mind. Designed as part of my Individual Project in Year 3 at Durham University.

## Goals:
- Simple to use software, with key features represented visually (rather than within lots of windows).
- All tools should be available within about 5 clicks
- Quality isn't lost when consistently editing photos (as we use the RAW photo as a basis)

## Notes:
### Frontend
This is the client side interface to the application. It's powered by ReactJS with React Router for routing.

Navigate to the folder ```frontend``` and then run:

```bash
npm install
```
Now, to start the webpack server, simply run:

```bash
npm start
```

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
