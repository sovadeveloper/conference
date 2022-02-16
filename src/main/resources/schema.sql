create sequence hibernate_sequence
    start with 1 increment by 1;

create table room (
    id bigint not null,
    number integer not null,
    primary key (id)
);

create table schedule (
    id bigint not null,
    end timestamp not null,
    start timestamp not null,
    room_id bigint,
    talk_id bigint,
    primary key (id)
);

create table talk (
    id bigint not null,
    text varchar(255) not null,
    title varchar(255) not null,
    primary key (id)
);

create table user (
    id bigint not null,
    active boolean not null,
    password varchar(255) not null,
    role varchar(255),
    username varchar(255) not null,
    primary key (id)
);

create table user_talk (
    talk_id bigint not null,
    user_id bigint not null,
    primary key (talk_id, user_id)
);

alter table room
    add constraint room_number_uk
    unique (number);

alter table user
    add constraint user_username_uk
    unique (username);

alter table schedule
    add constraint schedule_room_fk
    foreign key (room_id) references room;

alter table schedule
    add constraint schedule_talk_fk
    foreign key (talk_id) references talk;

alter table user_talk
    add constraint user_talk_user_fk
    foreign key (user_id) references user;

alter table user_talk
    add constraint user_talk_talk_fk
    foreign key (talk_id) references talk;
