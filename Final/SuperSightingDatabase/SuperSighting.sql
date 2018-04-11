create database if not exists supersighting;
drop database supersighting;

use supersighting;

create table SuperPower(
	SuperPowerID int primary key not null auto_increment,
    SuperPowerName varchar(50) not null
);


create table SuperPerson(
	SuperPersonID int primary key not null auto_increment,
    SuperName varchar(50) not null,
    Description varchar(1000) not null,
    TypeOfSuperPerson varchar(20) not null,
    SuperPowerID int not null,
    foreign key (SuperPowerID) references SuperPower(SuperPowerID)
);

create table Organization(
	OrganizationID int primary key not null auto_increment,
    TypeOfOrganization varchar(20) not null,
    OrganizationName varchar(50) not null,
    OrganizationDescription varchar(1000) not null,
    OrganizationAddress varchar(100) not null,
    OrganizationContact varchar(50) not null
);

create table Location(
	LocationID int not null primary key auto_increment,
    LocationName varchar(100) not null,
    LocationDescription varchar(1000) not null,
    Latitude decimal(9,6) not null,
    Longitude decimal(9,6) not null
);

create table Sighting(
	SightingID int not null primary key auto_increment,
    SightingDateTime datetime not null,
    LocationID int not null,
    foreign key (LocationID) references Location(LocationID)
);

create table SuperPersonOrganization(
	SuperPersonID int not null,
    OrganizationID int not null,
    primary key (SuperPersonID, OrganizationID),
    foreign key (SuperPersonID) references SuperPerson(SuperPersonID),
    foreign key (OrganizationID) references Organization(OrganizationID)
);

create table SuperPersonSighting(
	SuperPersonID int not null,
    SightingID int not null,
    primary key(SuperPersonID, SightingID),
    foreign key (SuperPersonID) references SuperPerson(SuperPersonID),
    foreign key (SightingID) references Sighting(SightingID)
);


    


    
    
	



