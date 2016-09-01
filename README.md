# Project2_Advanced_Web_app

#CS645 Spring 2015

#Advanced Web Application Development

#Programming Assignment #1

125 Points
#Due Date/Time:

Your project is due at 4:00 P.M San Diego time on Thursday, February 26th. As this is a large project, you should start on it right away. It will take longer to complete than you might first think.

The entry point for your project is a file called index.html. Make a new folder in your public_html/ folder called "proj1" and place the index.html in it. Thus, the first page of your project (the login screen) will be displayed if the user directs the browser to: http://jadran.sdsu.edu/~jadrnXXX/proj1

#Perl/CGI/Javascript/SQL Review Assignment:

This assignment is designed to review and reinforce fundamental principles of dynamic programming. The tools you may use for this project are: Perl (mod_perl only), xhtml/html 5, CSS 3, Javascript, and the MySQL database on opatija. You may use a Javascript library such as JQuery if you wish. For this project, you must use Perl for all scripts.

You will create an online store selling some type of merchandise. The type of products you choose to sell is entirely up to you. Some suggestions:

cameras
shoes
clothing
jewelry
DVDs
software
food products
Give some thought to your choice; you will be creating additional projects (#2 and #3) using this online retail model. You should design an attractive and appealing theme or Look and Feel for your project. Please review the Project Design Guidelines (link at left).

For this first assignment you will create a portion of an inventory application for your online retail site. This portion is an application to allow a buyer to create inventory records for new products that the retailer will sell. The application consists of the following:

#A login screen.  
The user must supply an authorized username/password pair to gain access to the system.
#Menu page. 
Once login succeeds, the user will be presented with a page that gives choices to:
New Inventory Record, add a new record
Edit Inventory Record change some or all of an existing record
Delete Inventory Record remove a record from the system
Log out of the system
Rather than a blank page with four links, load the New Inventory Record page as the default, with links clearly available for the other choices (tabs are a good choice here).
#Confirmation.
After any of the above actions, you must display a confirmation, recapping the information just submitted, along with a message that the submission was successful (or not as the case may be). This may be a new page, or you may use AJAX to display the information in the existing page.
#The Details:

#The Login Screen
Present the user with two fields, the username and password. The form should also have a clear and a submit button. If the user enters a valid username/password, the Menu page will load. If the login is invalid, then the login screen should reappear, along with a helpful error message.

Username/password pairs should be stored in a text file on the server (not in the MySQL database). The username should be stored in plain text, and the password should be stored in an encrypted form. You may not hardcode any username/password pairs in your Perl code. You must have at least five valid username/password pairs, one of which must be:

    username:   cs645
    password:   sp2015
    
You must use POST as the parameter passing method. Use Javascript to place the cursor on the username field when the page loads. Simple things can mean a lot for useability. The user should not have to grab the mouse and manually give focus to the username field.

You must save state. Access to the add/edit/delete portion of the system can only be allowed for authorized users. Note that it is not sufficient to display the add/edit/delete page on successful login. You must validate the user on submission of data.

#The New Inventory Item screen
This is the form for adding a new item to the online store's inventory. New items must be stored in the MySQL database on opatija.sdsu.edu. For this project you will create tables with the following fields, all of which are required, not optional. This means that the user must provide this information when entering an inventory record:

#SKU The stock number.
This is a seven digit string, with three capital letters, followed by a dash, followed by a three digit number. Examples: ARC-001, ZZF-992 etc. This will be the primary key in the inventory table, and as such it must be distinct (no duplicates). Retailers generally use a proprietary code to identify each item they sell. The code is meaningful only to the retailer. Example: item number at Costco
#Category 
Almost all types of products need a category. For example, camera categories might be: DSLR, Point and Shoot, Underwater/weather-proof, film etc. Some types of products may need subcategories. Add this if you need to. Example: Shoes, dress, casual, walking, athletic, and these categories may be valid for men, women, or children.
#Vendor 
The name of manufacturer or brand of the item.
Manufacturer's Identifier Mandatory field. This is either a name or item number used by the manufacturer to identify the product.
#Description 
One paragraph that describes the new item. It should be suitable for displaying on the consumer page that will come later. i.e. it should be geared toward customers.
Product Features A list-oriented specification of the features or properties of the inventory item.
#Cost 
Currency format, this is the price the retailer pays the manufacturer for the product. Note, the $ symbol should not be used with the cost.
#Retail 
Currency format, the retail price that customers will pay for the product. [ You will need to add inventory items for project submission. Please select reasonable values for the cost and retail prices. Set the retail price about 25% above cost. ]
#Product Image 
An image of the new inventory item which may be uploaded. Store the filename along with the rest of the fields in the form in the MySQL database, but store the actual image file in a folder on the server.
Here is an example record:

SKU:    WEH-094
Vendor: Nikon 
Manufacturer's Identifier:  D4
Description:    Enter the new flagship of Nikon's D-SLR lineup:  D4.  Engineered for
                professionals, D4 strikes an ideal balance between resolution, sensor
                size, image processing and ISO range.  Its newlhy designed FX-format
                sensor and EXPEED3 processor enables image capture up to 10 fts with
                full AE/AF performance and Full 1080p HD video.  Combine that with
                cutting-edge metering and AF systems, intelligently designed controls,
                an ultra-rugged body and multiple connectivity options, and D4 liberates
                the world's top-notch photographers and multimedia artist like never before.


Product Features:  

                FX full frame format
                16.2 megapixels
                10 FPS continuous shooting
                ISO range 100-12,800 (expandable to 204,800)
                HD video with stereo sound
                3.2" Clear View LCD screen (921,000 pixels)
                    
Cost:   4652.50
Retail: $5999.95
Product Image:  d4_camera.png  
Canon 5D Camera
You must use Javascript to validate the form. As much as possible, your Javascript should help the user enter correct information. Think carefully about how you implement the validation. Be sure to use Javascript to put focus on the first field when the page loads.

A note about your project database tables. Good design will require a minimum of three tables in your database: SKU, category, and vendor. You do not need to make any provision for creating/editing/deleting records from the category and vendor tables. This is an appropriate simplification. Any needed changes may be made by interacting with the database directly. This is not as uncommon as it may sound--buyers often need to submit requests to the system administrator for such actions.

#Edit Inventory Page
This screen allows the buying office to change any field in the form except for the SKU. You should allow the buyer to search for a record to modify, using the SKU field only. Populate the form with the existing data, which the buyer may change and save. Once an edit has been completed, the record must validate correctly. If the user needs to change an SKU, the record must be deleted and re-added.

#Delete Inventory Page
This screen allows the buyer to delete an inventory record from the system. You should allow the buyer to search for the record to delete by SKU only. Note: Delete should be allowed only in cases where a given SKU has had no activity. That is, to delete items that have never been stocked or sold. However, since we are creating this application first, you need not make any provision for this.

#Confirmation Page
Every action (add/edit/delete/logout) should result in a confirmation that displays the status of the action. If an error occurs, display a helpful error message. In any case, if you send a confirmation page, include at the top of the page a link to take the user back to the login screen or menu page as is appropriate. Note: you must insure that only authorized users have access to the system.

#Project Submission

For this project, you will submit two things:
Deploy your application to jadran. If you build your application on your home computer, you must insure that it is properly deployed on jadran.
Create an empty file in your public_html/proj1/ folder named 'finished'. If this file does not exist in your project folder, it will not be graded. Please see the Project Submission Guidelines (link at left).


