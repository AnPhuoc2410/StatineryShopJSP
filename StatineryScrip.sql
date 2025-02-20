USE [master]


CREATE DATABASE [UserManagement] 

USE [UserManagement] 


delete tblUsers  where 1=1

drop table tblUsers

CREATE TABLE [dbo].[tblUsers](
	[userID] [nvarchar](50) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[roleID] [nvarchar](50) NULL,
	[avatar] [nvarchar](255) NOT NULL,
	[status] [bit] NOT NULL DEFAULT 1,
	[authType] [nvarchar](50) NOT NULL DEFAULT 'Web'
	
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE tbl_Order(
orderID NVARCHAR(50) NOT NULL PRIMARY KEY,
userID NVARCHAR(50) NOT NULL,
total FLOAT NOT NULL,
date DATE NOT NULL,--YYYY-MM-DD
status BIT NOT NULL DEFAULT 1,
FOREIGN KEY (userID) REFERENCES tblUsers(userID),
)



CREATE TABLE tbl_Products(
productID NVARCHAR(50) NOT NULL PRIMARY KEY,
name NVARCHAR(50) NOT NULL,
price DECIMAL(10,2) NOT NULL,
quantity INT,
image nvarchar(50) NOT NULL,
status BIT NOT NULL DEFAULT 1,
)



CREATE TABLE tbl_OrderDetail(
orderID NVARCHAR(50) NOT NULL,
productID NVARCHAR(50) NOT NULL,
price FLOAT,
quantity INT,
status BIT NOT NULL DEFAULT 1,
PRIMARY KEY(orderID,productID),
FOREIGN KEY(orderID) REFERENCES tbl_Order(orderID),
FOREIGN KEY(productID) REFERENCES tbl_Products(productID),
)


INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],avatar, [status]) VALUES (N'admin', N'Toi la admin', N'1', N'AD','img/avatar.png', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],avatar, [status]) VALUES (N'Hoadnt', N'Hoa Doan', N'1', N'US','img/avatar.png', 1)
INSERT [tblUsers] ([userID],[fullName],[password],[roleID],avatar,[status]) VALUES (N'double',N'KR W',N'123','US','img/avatar.png',1)
INSERT [tblUsers] ([userID],[fullName],[password],[roleID],avatar,[status]) VALUES (N'build',N'KR Build',N'1','US','img/avatar.png',1)
INSERT [tblUsers] ([userID],[fullName],[password],[roleID],avatar,[status]) VALUES (N'phuoc',N'Phuoc Dao',N'1','US','img/avatar.png',1)
INSERT [tblUsers] ([userID],[fullName],[password],[roleID],avatar,[status]) VALUES (N'SE180581',N'An Phuoc',N'1','AD','img/yukino.jpg',1)
 
INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES (N'NTBK01',N'Notebook', 5.99, 50,'imgProducts/NoteBook.jpg', 1); 
INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES (N'BOKR01',N'Book', 10.60, 25,'imgProducts/BookJava.png', 1);  
INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES (N'CAP05',N'Capybara', 3.50, 25,'imgProducts/CapybaraBook.jpg', 1);  
INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES (N'CAL03',N'Cal', 20.50, 25,'imgProducts/Cal.jpg', 1);  
INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES (N'PR001', N'Pen', 1.25, 100,'imgProducts/Pen.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR002', N'Pencil', 0.75, 150,'imgProducts/blackpencil.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR003', N'Eraser', 0.50, 75,'imgProducts/Eraser.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR004', N'Notebook (Small)', 2.99, 50,'imgProducts/NoteBookSmall.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR005', N'Ruler', 4.50, 30,'imgProducts/Ruler.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR006', N'Highlighter', 1.99, 80,'imgProducts/Highlighter.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR007', N'Marker', 1.75, 60,'imgProducts/Marker.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR008', N'Stapler', 3.25, 25,'imgProducts/Stapler.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR009', N'Scissors', 2.50, 40,'imgProducts/Scissors.jpg', 1);
  INSERT INTO [tbl_Products] (productID, name, price, quantity,image, status) VALUES(N'PR010', N'Glue Stick', 1.00, 120,'imgProducts/Glue Stick.jpg', 1);

