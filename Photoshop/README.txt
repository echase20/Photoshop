Bird image - https://nationalzoo.si.edu/migratory-birds/news/small-bird-big-voice
Rhino image - https://animals.sandiegozoo.org/animals/white-rhinoceros

The ImageModel interface supports functionality for blurring, sharpening, applying sepia or monochrome filters, and exporting
the images that the model stores. It also can import a file given its name and add it to the model.
Each image is represented through the ImageApplications interface which supports more general filter and kernel applications,
and exporting the image as a file.
The filter and adjust methods take in FilterObjects and ImageAdjusters which allow the ImageApplications to apply
any filter or kernel as long as one of those objects is given.
The FilterObject interface allows for functionObjects that each only have one method, which returns a matrix to apply to each rgb vector.
The ImageAdjuster interface is similar but returns a kernel instead which might not be of length 3. 
Both of these interfaces only return their kernel or matrix because while our ImageApplications implementation class represents
images as an arraylist of an arraylist of pixels, other future ImageApplications might not, so the FilterObjects
and ImageAdjusters only return whats specific to their filter/adjustment, since no matter the implementation, their
matrix or kernel will always be required in some way.