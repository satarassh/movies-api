-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database

insert into movies (id, imdbId, title, genre, year, description) 
    values( 
        nextval('hibernate_sequence'), 
        'tt0993846',
        'The Wolf of Wall Street',
        'Comedy',
        2013,
        'Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.'
        );

insert into movies (id, imdbId, title, genre, year, description) 
    values( 
        nextval('hibernate_sequence'), 
        'tt1320253',
        'The Expendables',
        'Action',
        2010,
        'A CIA operative hires a team of mercenaries to eliminate a Latin dictator and a renegade CIA agent.'
        );

insert into pictures (id, url, movie_id) 
   values( 
        nextval('hibernate_sequence'),
        'https://flxt.tmsimg.com/assets/p9991602_k_v13_ab.jpg',
        'tt0993846'
        );

insert into pictures (id, url, movie_id) 
   values( 
        nextval('hibernate_sequence'),
        'https://flxt.tmsimg.com/assets/p9991602_k_h9_ab.jpg',
        'tt0993846'
        );

insert into pictures (id, url, movie_id) 
   values( 
        nextval('hibernate_sequence'),
        'https://flxt.tmsimg.com/assets/p3546099_p_v8_bc.jpg',
        'tt1320253'
        );
       