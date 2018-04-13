/******************************************************************************
 *  Name:     
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:       
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 5: Kd-Trees


/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/
	Our Node class encapsulates a left and right child, 4 Double fields representing the corresponding rectangle,
	a Boolean field informing us whether the Node splits the plane horizontally or vertically, and of course a Point2D key and generic Value.
	
	In total we estimate ~200N bytes for a tree with N nodes.
	
/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/
	We use a recursive implementation for range that first checks if the root is in the query Rectangle then traverses
	subtrees of the root if their corresponding rectangle intersects the query rectangle.

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/
	Our nearest neighbor algorithm determines, starting at the root and recursively travelling the tree, if a node's key is closer to the query point than the closest point discovered so far.
	If the query point is to the left of the current node, the left tree is searched first and vice versa. Subtrees which could not contain a closer point are not checked. 
	This is determined by comparing the distance between the corresponding rectangle and the current key with the closest distance found so far. 

/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Show the math how you used to determine
 *  the operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt		about 270					about 28.5 thousand

input1M.txt			about 16					about 13 thousand



/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
  There is a small bug where the size is not calculated correctly. After quite a bit of debugging it seems like all nodes given to put()
  are inserted into the tree. However, the size seems to usually be off by a few.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
	It took me awhile to realize that I needed to store all 4 dimensions of the corresponding and possibly unbounded rectangle
	on the Node class. I thought that, since an intersects() method would only need a min/max to calculate that's all I would need.
	Once I refactored put() to start explicitly stating each 'boundary' for each insert things started to make much more sense.

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/
Absolutely, Jack and I followed protocol ever step of the way.

Both Jack and I completed PointST individually. Then we collaborated on KdTreeST. Jack wrote the nearest() methods and tests
while I focused on getting put() and range() working correctly.

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on  how helpful the class meeting was and on how much you learned 
 * from doing the assignment, and whether you enjoyed doing it.
 *****************************************************************************/
 I was fairly surprised by the end results and testing how much faster KdTreeST was for nearest(). Even though it was stated
 since the beginning that that was what was going to happen it still took me by surprise. An Impressive concept indeed!