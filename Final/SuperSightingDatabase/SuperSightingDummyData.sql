use supersighting;

insert into SuperPower (SuperPowerName)
values
('Concussion Beams'),
('Energy Sourcing'),
('Inhuman Nature'),
('Superhuman Strength'),
('Physical Duplication'),
('Poison Generation'),
('Magnetism Manipulation'),
('Fire Manipulation');

insert into SuperPerson(SuperName, Description, TypeOfSuperPerson, SuperPowerId)
values
('Cyclops', 'Cyclops can emit powerful beams of energy from his eyes.', 'Evil', 1),
('Darkseid', 'His main power, the Omega Beams, is a form of energy that he fires from his eyes or hands as either a concussive force or a disintegrating energy which is capable of erasing living objects and organisms from existence.', 'Good', 1),
('Gambit', 'Gambit has the ability to mentally create, control, and manipulate pure kinetic energy to his desire.', 'Good', 2),
('Goku', 'Goku is a member of an extraterrestrial warrior race called the Saiyans, which is the reason for his superhuman strength', 'Evil', 3),
('Vegeta', 'Vegeta is a member of an extraterrestrial warrior race called the Saiyans, which is the reason for his superhuman strength', 'Good', 3),
('Hulk', 'Mild-mannered Bruce Banner was exposed to gamma radiation. You wouldnt like him when hes angry.', 'Green', 4),
('Naruto Uzumaki', 'A teen ninja from the fictional village of Konohagakure.', 'Japanese', 5),
('Poison Ivy', 'One of the worlds most notorious eco-terrorists, she uses plant toxins and mind-controlling pheromones for her criminal activities', 'Green', 6),
('Magneto', 'The character is a powerful mutant, one of a fictional subspecies of humanity born with superhuman abilities, who has the ability to generate and control magnetic fields.', 'Magnetic', 7),
('Princess Azula', 'Azula is the crown princess of the Fire Nation and a Firebending prodigy.', 'Pyromaniac', 8);

insert into Organization (TypeOfOrganization, OrganizationName, OrganizationDescription, OrganizationAddress, OrganizationContact)
values
('Good', 'Heroic Organization', 'A club for cool heroes!', '123 SuperStreet, New York, NY, Earth', 'hero@heroclub.com'),
('Bad', 'Villains Only', 'Lame Heroes stay out!', '9964 Lunar Crater, The Moon', 'none@urbusiness.com');

insert into SuperPersonOrganization(SuperPersonID, OrganizationID)
values
(1, 1),
(2, 2),
(3, 1),
(4, 2),
(5, 1),
(6, 1),
(7, 2),
(8, 2),
(9, 2),
(10, 2);

insert into Location (LocationName, LocationDescription, latitude, longitude)
values
('Central Park', 'New York Parks and Recreation', 180.000000, 180.000000),
('Golden Gate Bridge', 'The Pride of the West', 115.000000, 110.000000);

insert into Sighting (SightingDateTime, locationId)
values
('2010-01-01 12:12:12', 1),
('2010-01-02 12:12:12', 1),
('2010-01-03 12:12:12', 1),
('2011-01-01 12:12:12', 2),
('2012-01-01 12:12:12', 2),
('2018-03-20 12:12:12', 2);

insert into SuperPersonSighting(superPersonId, sightingId)
values
(1, 1), (1, 4), (1, 6),
(2, 1),
(3, 3),
(4, 3),
(9, 1), (9, 2);