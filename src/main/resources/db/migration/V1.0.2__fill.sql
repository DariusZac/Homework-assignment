use movie_wikidb;

INSERT INTO movie(id, name) VALUES(1, "Avatar");
INSERT INTO movie(id, name) VALUES(2, "Terminator Salvation");
INSERT INTO movie(id, name) VALUES(3, "Avengers: Endgame");

INSERT INTO main_actor(id, fullname, nationality, date_of_birth) VALUES(1, "Samuel Henry John Worthington", "Australian", "1976-8-2" );
INSERT INTO main_actor(id, fullname, nationality, date_of_birth) VALUES(2, "Zoe Saldana", "US", "1978-6-19" );
INSERT INTO main_actor(id, fullname, nationality, date_of_birth) VALUES(3, "Christopher Robert Evans", "US", "1981-6-13" );

INSERT INTO movie_actor(movie_id, actor_id) VALUES(1, 1);
INSERT INTO movie_actor(movie_id, actor_id) VALUES(2, 1);
INSERT INTO movie_actor(movie_id, actor_id) VALUES(1, 2);
INSERT INTO movie_actor(movie_id, actor_id) VALUES(3, 2);
INSERT INTO movie_actor(movie_id, actor_id) VALUES(3, 3);

INSERT INTO movie_detail(movie_id, description, budget, release_date) VALUES(1, "Avatar is a 2009 epic science fiction film directed, written, co-produced and co-edited by James Cameron", 246000000, "2009-12-18");
INSERT INTO movie_detail(movie_id, description, budget, release_date) VALUES(2, "Terminator Salvation is a 2009 American military science fiction action film directed by McG and written by John Brancato and Michael Ferris.", 200000000, "2009-04-21");
INSERT INTO movie_detail(movie_id, description, budget, release_date) VALUES(3, "Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers. Produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures, it is the direct sequel to Avengers: Infinity War.", 384000000, "2019-04-26");

INSERT INTO review(id, movie_id, reviewer, review, score) VALUES(1, 1, "Matt Conway", "Avatar still elicits much of the same wide-eyed wonderment.", 9);
INSERT INTO review(id, movie_id, reviewer, review, score) VALUES(2, 1, "Mike Massie", "The level of detail and creativity surrounding the look of director James Cameron's new world is simply astonishing.", 9);
INSERT INTO review(id, movie_id, reviewer, review, score) VALUES(3, 2, "Mike Massie", "No tricky time travel finds its way into the plot - and, perhaps, such complexities are exactly the ideas that are missing.", 6);
INSERT INTO review(id, movie_id, reviewer, review, score) VALUES(4, 2, "Keith Garlington", "I liked “Terminator Salvation” and while it’s not everyone’s cup of tea, I think it has a place in the series.", 8);
