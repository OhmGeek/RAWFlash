# Project Plan
A working document, containing the plan for the project.
Last updated: Saturday 14th October 2017

## Deliverables
According to the initial project specification, these are the following deliverables. These are important to follow, as they will be what the project will be graded against in terms of what should be delivered.

### Minimum Objectives
- [] Load DNG RAW files by upload
- [x] Exposure adjustment
- [] Web interface interacting with image processing server
- [x] Non-destructive image adjustment

#### Noise reduction methods:
- [ ] Gaussian
- [ ] Mean
- [ ] Median

### Intermediate Objectives
- [] Modern, friendly UX
- [] Spot healing implemented
- [] Haze Removal
- [] Cropping
- [] Rotating
- [] Exporting to other formats

### Advanced objectives
- [] Addressing potential scalability issues
- [] Implementing basic previews using built-in JPEG to improve QoS

## Plan for the project, going ahead:

First, prioritizing the remaining basic objectives is key.

We need to bring in the image storage on a separate server, so we can upload files, and then load them in using the server. Required steps to achieve this:

- [] Set up a storage server, allowing URL access.
- [] Adding code to the server to load files from the image and store them locally
- [] Export to the storage server

This will take a couple of weeks to achieve this.

Then, focus on the main web interface is needed. Initially in the plan we said to learn ReactJS/Redux, but this has changed to instead utilize VueJS due to the difficult nature of getting Webpack building with custom configurations. VueJS is nicer, provides a nicer code structure.

We need to then construct the following features:

- [] Static Sidebar Panel
- [] Allow various fields to be displayed in these sidebar forms
- [] Manage a queue of image operations to be carried out as JSON (according to server spec).
- [] Sending server commands to the server, and displaying the resulting image.


This will take a bit longer, as we need to both design the UX as well as working with Java on the server side to correctly communicate between client and server sides.

Once this is done, we can focus on the more feature heavy elements, namely adding filtering operations. This shouldn't be too diffuclt, as it just means slightly modifying the front and backend respectively to add an operation to our custom written image processing scripts. Haze removal too will be more server heavy, as we will need to study the best possible haze removal algorithms to use, how they work, and how best to implement them (including possible parallel methods).
