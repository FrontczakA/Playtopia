
INSERT INTO game (price, title, description, genre, platform, photo_url, in_stock)
SELECT data.price, data.title, data.description, data.genre, data.platform, data.photo_url, data.in_stock
FROM (
         SELECT 59.99 AS price, 'FIFA 22' AS title, 'Latest installment of the popular FIFA series. Enjoy enhanced gameplay mechanics and updated team rosters.' AS description, 'Sports' AS genre, 'XBOX ONE' AS platform, 'https://www.mediaexpert.pl/media/cache/resolve/gallery/images/30/3057684/FIFA-22-Gra-XBOX-ONE-front.jpg' AS photo_url, 100 AS in_stock
         UNION ALL
         SELECT 69.99, 'The Witcher 3', 'Epic fantasy RPG set in the world of The Witcher. Embark on a memorable journey through vast lands full of dangers and mysteries.', 'RPG', 'PS5', 'https://gamefaqs.gamespot.com/a/box/3/2/4/914324_front.jpg', 50
         UNION ALL
         SELECT 49.99, 'Super Mario RPG', 'Classic RPG featuring Mario and friends. Develop your skills, collect items, and battle enemies in this iconic Nintendo game!', 'RPG', 'Switch', 'https://www.krakow.gameover.pl/sklep/galerie/s/super-mario-rpg-switch-pre-or_33841.jpg', 80
         UNION ALL
         SELECT 39.99, 'The Elder Scrolls V: Skyrim', 'Open-world RPG set in the world of Skyrim. Experience countless adventures, fight powerful dragons, and shape the destiny of the kingdom.', 'RPG', 'PC', 'https://images.launchbox-app.com/70b93a35-1b79-424b-919f-6547dbd690d3.jpg', 120
         UNION ALL
         SELECT 49.99, 'Red Dead Redemption 2', 'Epic action-adventure set in the wild west. Play as Arthur Morgan and uncover the secrets of the frontier.', 'Action-Adventure', 'PS4', 'https://m.media-amazon.com/images/I/81UfsmVaUjL.jpg', 80
         UNION ALL
         SELECT 39.99, 'Total War: Napoleon', 'Strategic turn-based game from the Total War series. Lead Napoleon Bonaparte and conquer Europe in epic battles.', 'Strategy', 'PC', 'https://wiki.totalwar.com/images/thumb/b/b0/Ntw_ge.jpg/500px-Ntw_ge.jpg', 60
         UNION ALL
         SELECT 49.99, 'Assassins Creed Valhalla', 'Embark on a Viking journey in the latest Assassins Creed installment. Raid villages, build your settlement, and lead your clan to glory.', 'Action-Adventure', 'XBOX SERIES X', 'https://m.media-amazon.com/images/I/7131E1EEERL._AC_UF1000,1000_QL80_.jpg', 70
         UNION ALL
         SELECT 59.99, 'Cyberpunk 2077', 'Immerse yourself in a futuristic open-world RPG. Explore the bustling streets of Night City and uncover its dark secrets.', 'RPG', 'PC', 'https://m.media-amazon.com/images/I/81qAWv0I4CL._AC_UF894,1000_QL80_.jpg', 40
         UNION ALL
         SELECT 39.99, 'Minecraft', 'Create and explore your own blocky world in this sandbox game. Build structures, mine resources, and unleash your creativity!', 'Adventure', 'Switch', 'https://m.media-amazon.com/images/I/81gJUSx-TjL.jpg', 90
         UNION ALL
         SELECT 29.99, 'Rocket League', 'Unique combination of soccer and vehicular mayhem. Score goals, perform aerial acrobatics, and customize your car in this fast-paced game!', 'Sports', 'PS4', 'https://a.allegroimg.com/original/1160d6/64fb2f8e4146b9d6faf1f81af187/SONY-PS4-ROCKET-LEAGUE', 110
         UNION ALL
         SELECT 59.99, 'Call of Duty: Modern Warfare 3', 'Free-to-play battle royale experience set in the Call of Duty universe. Drop into massive maps, gather weapons, and outlast your opponents!', 'Shooter', 'PC', 'https://5.allegroimg.com/s1024/0c5cb6/7d22876943f7a292b56b534b3c35', 60
         UNION ALL
         SELECT 49.99, 'Animal Crossing: New Horizons', 'Escape to a deserted island and create your own paradise in this charming life simulation game. Decorate your home, make friends, and enjoy island life!', 'Simulation', 'Switch', 'https://www.mediaexpert.pl/media/cache/resolve/gallery/images/21/2161785/Animal-Crossing-New-Horizons-Gra-NINTENDO-SWITCH-front.jpg', 70
         UNION ALL
         SELECT 39.99, 'God of War', 'Embark on a journey with Kratos, a fallen god seeking redemption. Battle mythical creatures, solve puzzles, and explore a stunning world inspired by Norse mythology.', 'Action-Adventure', 'PS4', 'https://6.allegroimg.com/s1024/0c623b/8047721d4fa6a44bff27c8e4d646', 80
         UNION ALL
         SELECT 29.99, 'Among Us', 'Social deduction game where players work together to complete tasks on a spaceship, while trying to identify impostors among them. Can you survive?', 'Party', 'PC', 'https://cdn1.epicgames.com/salesEvent/salesEvent/amogusportrait_1200x1600-66ad0e4d363e1c92f9f8aae67a96dd31', 120
         UNION ALL
         SELECT 39.99, 'The Legend of Zelda: Breath of the Wild', 'Explore the vast kingdom of Hyrule in this epic adventure game. Solve puzzles, defeat enemies, and uncover the secrets of the ancient land.', 'Action-Adventure', 'Switch', 'https://image.ceneostatic.pl/data/products/49538999/i-the-legend-of-zelda-breath-of-the-wild-gra-ns.jpg', 70
     ) AS data
         LEFT JOIN game ON game.title = data.title
WHERE game.title IS NULL;

INSERT INTO app_user (username, password, email, name, surname, role)
SELECT 'admin', '$2a$10$cJwiMgpjL6DusmkauKSnOeUJft7UC3W6ttFD1tjaLowIqXlKA5X8G', 'admin@admin.com', 'admin', 'admin', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM app_user WHERE username = 'admin');