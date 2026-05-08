-- V2: Seed menu categories and items from ABQ Desi Bites menu

INSERT INTO menu_categories (name, description, sort_order) VALUES
('Dosa Specials',   'Roasted crispy pan cakes served with peanut chutney, sambar, tomato chutney, or ginger chutney', 1),
('Idly Specials',   'Steamed rice cakes served with peanut chutney, sambar, tomato chutney, or ginger chutney', 2),
('Evening Specials','Evening snacks and street food favorites', 3),
('Weekend Specials','Available Friday, Saturday, and Sunday only', 4),
('Desserts',        'Any 2 desserts offered every day', 5);

-- Dosa Specials (category_id = 1)
INSERT INTO menu_items (category_id, name, description, price, sort_order) VALUES
(1, 'Plain Dosa',                'Classic crispy dosa served with traditional chutneys & sambar', 6.99, 1),
(1, 'Ghee Karam Dosa',           'Crispy dosa topped with caramelized onions', 7.99, 2),
(1, 'Uttapam',                   'Double decker dosa topped with veggies served with chutneys', 8.99, 3),
(1, 'Mysore Masala Dosa',        'A spicy kick to the classic potato-filled dosa', 9.99, 4),
(1, 'Set Dosa (3 pcs)',          'Soft, fluffy dosas served with chutneys', 10.99, 5),
(1, 'Hyderabad Masala Dosa',     'A Taste of Hyderabad''s Streets: Spicy, buttery, and featuring our signature Upma filling', 11.99, 6),
(1, 'Egg Dosa',                  'Dosa layered with egg and spices', 12.99, 7),
(1, 'NM Cheese Dosa',            'New Mexico fusion – dosa filled with melted cheese', 12.99, 8),
(1, 'Chennai Chicken Keema Dosa','Dosa stuffed with spicy minced chicken filling', 13.99, 9);

-- Idly Specials (category_id = 2)
INSERT INTO menu_items (category_id, name, description, price, sort_order) VALUES
(2, 'Plain Idly (3 pcs)',        'Soft steamed idly served with traditional chutneys and sambar', 8.99, 1),
(2, 'Ghee Karam Idly (3 pcs)',   'Idly topped with ghee and spicy karam powder for bold flavor', 10.99, 2),
(2, 'Chennai Sambar Idly (2 pcs)','Idly soaked in authentic Chennai-style sambar', 9.99, 3);

-- Evening Specials (category_id = 3)
INSERT INTO menu_items (category_id, name, description, price, sort_order) VALUES
(3, 'Guntha Ponganalu',  'Soft, bite-sized dosa dumplings served with chutneys', 7.99, 1),
(3, 'Punugulu',          'Soft, crispy dumplings served with chutneys', 8.99, 2),
(3, 'Veg Pakoda',        'Crispy mixed-vegetable fritters seasoned with traditional spices and fried to golden perfection', 8.99, 3),
(3, 'Chicken Pakodi',    'Crispy, golden fried chicken bites seasoned with authentic South Indian spices', 11.99, 4),
(3, 'Vada Pav',          'A spicy potato fritter tucked inside a soft bun, served with chutneys and a kick of masala', 4.99, 5),
(3, 'Mirchi Bajji (2 Pcs)', 'Large green chilies dipped in spiced gram-flour batter, deep-fried until golden, and served with tangy chutney', 5.99, 6);

-- Weekend Specials (category_id = 4)
INSERT INTO menu_items (category_id, name, description, price, is_weekend, sort_order) VALUES
(4, 'Hyderabadi Chicken Biryani', 'A royal blend of tender chicken, aromatic basmati rice, saffron, and traditional Hyderabadi spices. Slow-cooked to perfection for rich flavor in every bite.', 14.99, TRUE, 1),
(4, 'Baghara Rice Chicken Curry', 'Fragrant basmati rice with traditional chicken curry on side. Simple, hearty, and packed with home-style taste', 12.99, TRUE, 2),
(4, 'Chicken Curry',              'Classic home-style chicken curry', 7.99, TRUE, 3),
(4, 'Veg Momo',                   'Soft, steamed dumplings filled with your choice of veggies, served with spicy Nepali chutney', 9.99, TRUE, 4),
(4, 'Chicken Momo',               'Soft, steamed dumplings filled with chicken, served with spicy Nepali chutney', 11.99, TRUE, 5);

-- Desserts (category_id = 5)
INSERT INTO menu_items (category_id, name, description, price, sort_order) VALUES
(5, 'Gulab Jamun',      'Soft, warm milk-solid dumplings soaked in fragrant sugar syrup', 4.99, 1),
(5, 'Kaddu Ka Kheer',   'A traditional Hyderabadi dessert made with grated bottle gourd simmered in milk, sugar, and aromatic cardamom, garnished with nuts for a rich, comforting flavor', 6.99, 2),
(5, 'Kesari (Sheera)',  'Aromatic semolina dessert cooked with ghee, saffron, and nuts', 4.99, 3),
(5, 'Kheer (Rice Pudding)', 'Traditional creamy rice pudding flavored with cardamom and garnished with nuts', 4.99, 4),
(5, 'Double Ka Meetha', 'A Hyderabadi classic: fried bread soaked in saffron milk and garnished with dry fruits', 4.99, 5),
(5, 'Carrot Halwa',     'Slow-cooked grated carrots with milk, ghee, nuts, a rich, warm delight', 5.99, 6),
(5, 'Ginger Chai',      'Freshly brewed Indian tea infused with crushed ginger for a warm, soothing, and aromatic flavor (Dairy whole milk used)', 1.99, 7),
(5, 'Mango Lassi',      'A refreshing blend of ripe mangoes, creamy yogurt, and a touch of cardamom. Smooth, sweet, and perfect with any meal.', 4.99, 8);
