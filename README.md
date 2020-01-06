# assignment-spring

This is my spring api for the assignment. It includes the two Models of the api. The item and the Inventory Item. 

The Item have 3 fields. the name and details of type string, and the item_no which is the primary key of 
these schema. 

The Inventory Item includes 3 fields also. the inventory_code(the PK) of type UUID, the amount of type int and 
the item_no which is the foreign key that let us join the Inventory item with the item details like name and details.

The Item cascading the Inventory Item. whick mean that every time an item is deleted from the table, the 
Inventory Item also gets deleted if it exists in the table also because they have one-toone realtion where
Item mapping the inventory item. 

The api includes connection to swagger api documantation tool and H2 DB via The JPA library.

