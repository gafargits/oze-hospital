DROP TABLE IF EXISTS HOSPITAL_STAFF;
DROP TABLE IF EXISTS HOSPITAL_PATIENT;

CREATE TABLE `hospital_staff` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(50) UNIQUE NOT NULL,
  `password` TEXT NOT NULL,
  `staff_id` uuid default random_uuid(),
  `registration_date` Date default CURRENT_DATE(),
  PRIMARY KEY (`id`)
);

create sequence oze_staff_seq increment 1 start 1000;

CREATE TABLE `hospital_patient` (
    `id` INT(11) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `dob` Date default CURRENT_DATE(),
    `last_visit_date` Date default CURRENT_DATE(),
    PRIMARY KEY (`id`)
);


create sequence oze_patient_seq increment 1 start 1;