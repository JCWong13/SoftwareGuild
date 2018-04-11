drop database HotelCalifornia;
create database if not exists HotelCalifornia;

use HotelCalifornia;

create table RoomDetail(
	RoomDetailID int primary key auto_increment,
    TypeOfBed varchar(25) not null,
    NumberOfBeds int not null
);

create table Rate(
	RateID int primary key auto_increment,
    StartDate datetime not null,
    EndDate datetime not null,
    Price decimal(10,2) not null
);

create table Amenity(
	AmenityID int primary key auto_increment,
    TypeOfAmenity varchar(300) not null
);

create table AddOn(
	AddOnID int primary key auto_increment,
    AddOnType varchar(50) not null
);

create table Price(
	PriceID int primary key auto_increment,
    AddOnDate datetime not null,
    StartDate datetime not null,
    EndDate datetime not null,
    QuantityOfAddOnType int not null,
    Price decimal(10,2) not null
);

create table CustomerType(
	CustomerTypeID int primary key auto_increment,
	TypeOfCustomer varchar(200) not null
);

create table Location(
	LocationID int primary key auto_increment,
    City varchar(200) not null,
    State varchar(200) null,
    Country varchar(200) not null,
    Planet varchar(200) not null
);

create table Promotion(
	PromotionID int primary key auto_increment,
    PromotionName varchar(300) not null,
    PromotionFlat decimal null,
    PromotionPercent decimal null,
    PromotionStart datetime not null,
    PromotionEnd datetime not null
);

create table Room(
	RoomID int primary key auto_increment,
    RoomNumber int not null,
    FloorNumber int not null,
    MaxOccupancy int not null,
    Smoking boolean not null,
    RoomDetailID int not null,
    LocationID int not null,
    foreign key (RoomDetailID) references RoomDetail(RoomDetailID),
    foreign key (LocationID) references Location(LocationID)
);

create table Customer(
	CustomerID int primary key auto_increment,
    FirstName varchar(50) not null,
    LastName varchar(50) not null,
    DateOfBirth date not null,
    Phone varchar(50) not null,
    Address varchar(300) not null,
    City varchar(300) not null,
    State varchar(300) null,
    Country varchar(300) not null,
    Zipcode varchar(100) null,
    Planet varchar(300) null,
    Email varchar(300) null,
    CustomerTypeID int not null,
    foreign key (CustomerTypeID) references CustomerType(CustomerTypeID)
);

create table Reservation(
	ReservationID int primary key auto_increment,
    DateOfReservation datetime not null,
    DateCheckIn datetime not null,
    DateCheckOut datetime not null,
    ReservationActive boolean not null,
    CustomerID int not null,
    foreign key (CustomerID) references Customer(CustomerID)
);

create table Guest(
	GuestID int primary key auto_increment,
    FirstName varchar(50) not null,
    LastName varchar(50) not null,
    Age int not null,
    ReservationID int,
    foreign key (ReservationID) references Reservation(ReservationID)
);

create table Bill(
	BillID int primary key auto_increment,
    Subtotal decimal(10,2) not null,
    Tax decimal(5,2) not null,
    Total decimal(10,2) not null,
    ReservationID int not null,
    foreign key (ReservationID) references Reservation(ReservationID)
);

create table BillOrderDetail(
	BillOrderDetailID int primary key auto_increment,
	LineItem varchar(200) not null,
    Price decimal(10,2) not null,
    Notes varchar(1000) not null,
    BillID int not null,
    foreign key (BillID) references Bill(BillID)
);

create table RoomRate(
	RoomID int not null,
    RateID int not null,
    primary key (RoomID, RateID),
    foreign key (RoomID) references Room(RoomID),
    foreign key (RateID) references Rate(RateID)
);

create table AddOnRoom(
	AddOnID int not null,
    RoomID int not null,
    primary key (AddOnID, RoomID),
    foreign key (AddOnID) references AddOn(AddonID),
    foreign key (RoomID) references Room(RoomID)
);

create table AddOnPrice(
	AddOnID int not null,
    PriceID int not null,
    primary key (AddOnID, PriceID),
    foreign key (AddOnID) references AddOn(AddOnID),
    foreign key (PriceID) references Price(PriceID)
);

create table AddOnBill(
	AddOnID int not null,
    BillID int not null,
    primary key (AddOnID, BillID),
    foreign key (AddOnID) references AddOn(AddOnID),
    foreign key (BillID) references Bill(BillID)
);

create table ReservationPromotion(
	ReservationID int not null,
    PromotionID int not null,
    primary key (ReservationID, PromotionID),
    foreign key (ReservationID) references Reservation(ReservationID),
    foreign key (PromotionID) references Promotion(PromotionID)
);

create table RoomReservation(
	RoomID int not null,
    ReservationID int not null,
    primary key (RoomID, ReservationID),
    foreign key (RoomID) references Room(RoomID),
    foreign key (ReservationID) references Reservation(ReservationID)
);

create table RoomAmenity(
	RoomID int not null,
    AmenityID int not null,
    primary key (RoomID, AmenityID),
    foreign key(RoomID) references Room(RoomID),
    foreign key(AmenityID) references Amenity(AmenityID)
);







