-- ── ELOF MILESTONES (Head Start ELOF, 5 official domains) ───────────────────

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000001', 'LL-1', 'Language and Literacy', '3-4 years', 'Child shows awareness that letters are symbols that represent sounds in spoken language')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000002', 'LL-2', 'Language and Literacy', '3-4 years', 'Child identifies the sounds that letters represent — phonemic awareness')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000003', 'LL-3', 'Language and Literacy', '4-5 years', 'Child recognizes and names all letters of the alphabet in varied contexts')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000004', 'COG-1', 'Cognition', '3-4 years', 'Child counts up to 5 objects with one-to-one correspondence')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000005', 'COG-2', 'Cognition', '3-4 years', 'Child counts up to 10 objects with one-to-one correspondence')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000006', 'COG-3', 'Cognition', '4-5 years', 'Child recognizes written numerals 0 through 10 and connects each to a quantity')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000007', 'COG-4', 'Cognition', '3-4 years', 'Child identifies and names basic two-dimensional shapes: circle, square, triangle, rectangle')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000008', 'COG-5', 'Cognition', '3-4 years', 'Child sorts and classifies objects by a single observable attribute such as color, shape, or size')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000009', 'COG-6', 'Cognition', '4-5 years', 'Child classifies objects into categories and can explain the sorting rule used')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000010', 'ATL-1', 'Approaches to Learning', '3-5 years', 'Child persists with tasks that require effort and tries again after making a mistake')
ON CONFLICT (code) DO NOTHING;

INSERT INTO elof_milestone (id, code, domain, age_range, description) VALUES
('a1000000-0000-0000-0000-000000000011', 'SED-1', 'Social and Emotional Development', '3-4 years', 'Child recognizes and names their own feelings and the feelings of others')
ON CONFLICT (code) DO NOTHING;


-- ── LETTERS — Gettman phonetic groups, taught by sound not name ───────────────
-- Group 1 — s, m, a, t  (difficulty 1) — most phonetically productive starting set
-- Group 2 — c, r, i, p  (difficulty 2)
-- Group 3 — b, f, o, g  (difficulty 2)
-- Group 4 — h, j, u, l  (difficulty 3)
-- Group 5 — d, w, e, n  (difficulty 3)
-- Group 6 — k, q, v, x, y, z (difficulty 4) — least common, introduced last

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000001', 'LETTER', 's', 'Letter S', 1, 'Language and Literacy', 'sun')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000002', 'LETTER', 'm', 'Letter M', 1, 'Language and Literacy', 'moon')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000003', 'LETTER', 'a', 'Letter A', 1, 'Language and Literacy', 'apple')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000004', 'LETTER', 't', 'Letter T', 1, 'Language and Literacy', 'tree')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000005', 'LETTER', 'c', 'Letter C', 2, 'Language and Literacy', 'cat')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000006', 'LETTER', 'r', 'Letter R', 2, 'Language and Literacy', 'rabbit')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000007', 'LETTER', 'i', 'Letter I', 2, 'Language and Literacy', 'igloo')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000008', 'LETTER', 'p', 'Letter P', 2, 'Language and Literacy', 'penguin')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000009', 'LETTER', 'b', 'Letter B', 2, 'Language and Literacy', 'ball')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000010', 'LETTER', 'f', 'Letter F', 2, 'Language and Literacy', 'fish')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000011', 'LETTER', 'o', 'Letter O', 2, 'Language and Literacy', 'orange')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000012', 'LETTER', 'g', 'Letter G', 2, 'Language and Literacy', 'grapes')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000013', 'LETTER', 'h', 'Letter H', 3, 'Language and Literacy', 'hat')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000014', 'LETTER', 'j', 'Letter J', 3, 'Language and Literacy', 'jar')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000015', 'LETTER', 'u', 'Letter U', 3, 'Language and Literacy', 'umbrella')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000016', 'LETTER', 'l', 'Letter L', 3, 'Language and Literacy', 'lion')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000017', 'LETTER', 'd', 'Letter D', 3, 'Language and Literacy', 'dog')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000018', 'LETTER', 'w', 'Letter W', 3, 'Language and Literacy', 'whale')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000019', 'LETTER', 'e', 'Letter E', 3, 'Language and Literacy', 'elephant')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000020', 'LETTER', 'n', 'Letter N', 3, 'Language and Literacy', 'nest')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000021', 'LETTER', 'k', 'Letter K', 4, 'Language and Literacy', 'kite')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000022', 'LETTER', 'q', 'Letter Q', 4, 'Language and Literacy', 'queen')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000023', 'LETTER', 'v', 'Letter V', 4, 'Language and Literacy', 'violin')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000024', 'LETTER', 'x', 'Letter X', 4, 'Language and Literacy', 'xylophone')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000025', 'LETTER', 'y', 'Letter Y', 4, 'Language and Literacy', 'yak')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('b1000000-0000-0000-0000-000000000026', 'LETTER', 'z', 'Letter Z', 4, 'Language and Literacy', 'zebra')
ON CONFLICT DO NOTHING;


-- ── NUMBERS — Gettman: quantity before symbol, 1-5 first ─────────────────────

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000001', 'NUMBER', '1', 'Number 1', 1, 'Cognition', 'one apple')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000002', 'NUMBER', '2', 'Number 2', 1, 'Cognition', 'two birds')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000003', 'NUMBER', '3', 'Number 3', 1, 'Cognition', 'three flowers')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000004', 'NUMBER', '4', 'Number 4', 1, 'Cognition', 'four strawberries')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000005', 'NUMBER', '5', 'Number 5', 1, 'Cognition', 'five stars')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000006', 'NUMBER', '6', 'Number 6', 2, 'Cognition', 'six fish')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000007', 'NUMBER', '7', 'Number 7', 2, 'Cognition', 'seven butterflies')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000008', 'NUMBER', '8', 'Number 8', 2, 'Cognition', 'eight balloons')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000009', 'NUMBER', '9', 'Number 9', 2, 'Cognition', 'nine dots')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('c1000000-0000-0000-0000-000000000010', 'NUMBER', '10', 'Number 10', 2, 'Cognition', 'ten fingers')
ON CONFLICT DO NOTHING;


-- ── SHAPES — Gettman sensorial sequence: basic first, complex second ──────────

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000001', 'SHAPE', 'circle', 'Circle', 1, 'Cognition', 'circle shape')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000002', 'SHAPE', 'square', 'Square', 1, 'Cognition', 'square shape')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000003', 'SHAPE', 'triangle', 'Triangle', 1, 'Cognition', 'triangle shape')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000004', 'SHAPE', 'rectangle', 'Rectangle', 1, 'Cognition', 'rectangle shape')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000005', 'SHAPE', 'oval', 'Oval', 2, 'Cognition', 'oval shape')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000006', 'SHAPE', 'diamond', 'Diamond', 2, 'Cognition', 'diamond shape')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000007', 'SHAPE', 'pentagon', 'Pentagon', 3, 'Cognition', 'pentagon shape')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('d1000000-0000-0000-0000-000000000008', 'SHAPE', 'hexagon', 'Hexagon', 3, 'Cognition', 'hexagon shape')
ON CONFLICT DO NOTHING;


-- ── CATEGORIES — Gettman culture: living/non-living, then subcategories ───────

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000001', 'CATEGORY', 'living', 'Living Things', 1, 'Cognition', 'living things nature')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000002', 'CATEGORY', 'non-living', 'Non-Living Things', 1, 'Cognition', 'objects household')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000003', 'CATEGORY', 'land-animals', 'Land Animals', 2, 'Cognition', 'land animals')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000004', 'CATEGORY', 'water-animals', 'Water Animals', 2, 'Cognition', 'water animals fish')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000005', 'CATEGORY', 'air-animals', 'Animals That Fly', 2, 'Cognition', 'birds flying animals')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000006', 'CATEGORY', 'fruits', 'Fruits', 2, 'Cognition', 'fresh fruits')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000007', 'CATEGORY', 'vegetables', 'Vegetables', 2, 'Cognition', 'fresh vegetables')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000008', 'CATEGORY', 'clothing', 'Clothing', 3, 'Cognition', 'childrens clothing')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000009', 'CATEGORY', 'instruments', 'Musical Instruments', 3, 'Cognition', 'musical instruments')
ON CONFLICT DO NOTHING;

INSERT INTO curriculum_content (id, type, value, display_name, difficulty_level, elof_domain, pixabay_keyword) VALUES
('e1000000-0000-0000-0000-000000000010', 'CATEGORY', 'tools', 'Tools', 3, 'Cognition', 'tools workshop')
ON CONFLICT DO NOTHING;