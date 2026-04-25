const API = 'http://localhost:8080/api';
const CHILD_ID = 'f2000000-0000-0000-0000-000000000001';

const GAME_COLORS = {
  'sound-safari':  '#1A7CE8',
  'berry-basket':  '#F07820',
  'shape-village': '#8830E8',
  'sorting-tray':  '#00A651'
};
const GAME_NAMES = {
  'sound-safari':  'Sound Safari',
  'berry-basket':  'Berry Basket',
  'shape-village': 'Shape Village',
  'sorting-tray':  'Sorting Tray'
};
const GAME_TYPE_MAP = {
  'sound-safari': 'SOUND_SAFARI',
  'berry-basket': 'BERRY_BASKET',
  'shape-village': 'SHAPE_VILLAGE',
  'sorting-tray': 'SORTING_TRAY'
};

const EMOJI_MAP = {
  'sun': '☀️', 'moon': '🌙', 'apple': '🍎', 'tree': '🌳',
  'cat': '🐱', 'rabbit': '🐰', 'igloo': '🏔️', 'penguin': '🐧',
  'ball': '⚽', 'fish': '🐟', 'orange': '🍊', 'grapes': '🍇',
  'hat': '🎩', 'jar': '🫙', 'umbrella': '☂️', 'lion': '🦁',
  'dog': '🐶', 'whale': '🐳', 'elephant': '🐘', 'nest': '🪹',
  'kite': '🪁', 'queen': '👑', 'violin': '🎻', 'xylophone': '🎵',
  'yak': '🐂', 'zebra': '🦓',
  'one apple': '🍎', 'two birds': '🐦', 'three flowers': '🌸',
  'four strawberries': '🍓', 'five stars': '⭐', 'six fish': '🐟',
  'seven butterflies': '🦋', 'eight balloons': '🎈', 'nine dots': '🔵',
  'ten fingers': '✋', 'eleven stars': '⭐', 'twelve hearts': '❤️',
  'thirteen dots': '🔵', 'fourteen flowers': '🌸', 'fifteen balloons': '🎈',
  'sixteen fish': '🐟', 'seventeen birds': '🐦', 'eighteen apples': '🍎',
  'nineteen stars': '⭐', 'twenty dots': '🔵',
  'car': '🚗', 'house': '🏠', 'bird': '🐦', 'flower': '🌸',
  'toy': '🧸', 'rock': '🪨', 'cow': '🐮', 'duck': '🦆',
  'butterfly': '🦋', 'strawberry': '🍓', 'carrot': '🥕',
  'shirt': '👕', 'guitar': '🎸', 'hammer': '🔨',
  'bus': '🚌', 'star': '⭐', 'book': '📚',
  'cup': '☕', 'shoe': '👟', 'cake': '🎂', 'boat': '⛵',
  'frog': '🐸', 'bear': '🐻', 'horse': '🐴', 'train': '🚂',
  'plane': '✈️', 'pizza': '🍕', 'banana': '🍌', 'milk': '🥛',
  'sock': '🧦', 'key': '🔑',
  'heart shape': '❤️', 'star shape': '⭐', 'cross shape': '✚',
  'crescent shape': '🌙', 'arrow shape': '➡️',
  'living things nature': '🌿', 'objects household': '🪑',
  'land animals': '🦁', 'water animals fish': '🐠',
  'birds flying animals': '🦅', 'fresh fruits': '🍎',
  'fresh vegetables': '🥕', 'childrens clothing': '👕',
  'musical instruments': '🎸', 'tools workshop': '🔧'
};

function getEmoji(keyword) {
  return EMOJI_MAP[keyword] || EMOJI_MAP[keyword.split(' ')[0]] || '🎯';
}

const SHAPE_SVGS = {
  circle:    '<svg width="100" height="100" viewBox="0 0 100 100"><circle cx="50" cy="50" r="44" fill="#FF6B6B" stroke="white" stroke-width="3"/></svg>',
  square:    '<svg width="100" height="100" viewBox="0 0 100 100"><rect x="8" y="8" width="84" height="84" rx="14" fill="#4ECDC4" stroke="white" stroke-width="3"/></svg>',
  triangle:  '<svg width="100" height="100" viewBox="0 0 100 100"><polygon points="50,8 92,92 8,92" fill="#FFE66D" stroke="white" stroke-width="3" stroke-linejoin="round"/></svg>',
  rectangle: '<svg width="120" height="80" viewBox="0 0 120 80"><rect x="6" y="6" width="108" height="68" rx="14" fill="#A78BFA" stroke="white" stroke-width="3"/></svg>',
  oval:      '<svg width="120" height="80" viewBox="0 0 120 80"><ellipse cx="60" cy="40" rx="54" ry="34" fill="#FB923C" stroke="white" stroke-width="3"/></svg>',
  diamond:   '<svg width="100" height="100" viewBox="0 0 100 100"><polygon points="50,4 96,50 50,96 4,50" fill="#34D399" stroke="white" stroke-width="3" stroke-linejoin="round"/></svg>',
  pentagon:  '<svg width="100" height="100" viewBox="0 0 100 100"><polygon points="50,4 96,36 78,92 22,92 4,36" fill="#60A5FA" stroke="white" stroke-width="3" stroke-linejoin="round"/></svg>',
  hexagon:   '<svg width="100" height="100" viewBox="0 0 100 100"><polygon points="50,4 92,26 92,74 50,96 8,74 8,26" fill="#F472B6" stroke="white" stroke-width="3" stroke-linejoin="round"/></svg>',
  star:      '<svg width="100" height="100" viewBox="0 0 100 100"><polygon points="50,5 61,35 95,35 68,57 79,91 50,70 21,91 32,57 5,35 39,35" fill="#FFE66D" stroke="white" stroke-width="3" stroke-linejoin="round"/></svg>',
  heart:     '<svg width="100" height="100" viewBox="0 0 100 100"><path d="M50,85 C50,85 10,55 10,30 C10,15 22,5 35,5 C42,5 48,9 50,13 C52,9 58,5 65,5 C78,5 90,15 90,30 C90,55 50,85 50,85Z" fill="#FF6B6B" stroke="white" stroke-width="3"/></svg>',
  cross:     '<svg width="100" height="100" viewBox="0 0 100 100"><rect x="38" y="8" width="24" height="84" rx="6" fill="#60A5FA" stroke="white" stroke-width="3"/><rect x="8" y="38" width="84" height="24" rx="6" fill="#60A5FA" stroke="white" stroke-width="3"/></svg>',
  crescent:  '<svg width="100" height="100" viewBox="0 0 100 100"><path d="M65,15 C40,15 20,33 20,55 C20,77 40,95 65,95 C45,85 32,71 32,55 C32,39 45,25 65,15Z" fill="#A78BFA" stroke="white" stroke-width="3"/></svg>',
  arrow:     '<svg width="100" height="100" viewBox="0 0 100 100"><polygon points="10,38 60,38 60,20 95,50 60,80 60,62 10,62" fill="#34D399" stroke="white" stroke-width="3" stroke-linejoin="round"/></svg>',
};

let currentGame = null, currentSession = null, currentQuestion = null;
let questionNumber = 1, answered = false;

function showScreen(id) {
  document.querySelectorAll('.screen').forEach(s => s.classList.remove('active'));
  document.getElementById('screen-' + id).classList.add('active');
}

function showMenu() {
  showScreen('menu');
  currentSession = null; questionNumber = 1; answered = false;
  document.getElementById('activity-card').style.display = 'none';
}

async function startGame(gameType) {
  currentGame = gameType; questionNumber = 1; answered = false;
  const bar = document.getElementById('game-bar');
  const body = document.getElementById('game-body');
  bar.style.background = GAME_COLORS[gameType];
  body.style.background = GAME_COLORS[gameType];
  document.getElementById('game-title').textContent = GAME_NAMES[gameType];
  try {
    const res = await fetch(`${API}/game/${gameType}/start/${CHILD_ID}`, { method: 'POST' });
    currentSession = await res.json();
    showScreen('game');
    await loadQuestion();
  } catch(e) { console.error('Failed to start:', e); }
}

async function loadQuestion() {
  answered = false;
  document.getElementById('feedback').style.display = 'none';
  document.getElementById('choices').innerHTML = '';
  try {
    const res = await fetch(`${API}/game/${currentGame}/question/${currentSession.id}/${questionNumber}`);
    currentQuestion = await res.json();
    renderQuestion(currentQuestion);
  } catch(e) { console.error('Failed to load:', e); }
}

function renderQuestion(q) {
  const pct = ((questionNumber - 1) / q.totalQuestions) * 100;
  document.getElementById('prog-fill').style.width = pct + '%';
  document.getElementById('q-count').textContent = `Question ${questionNumber} of ${q.totalQuestions}`;
  document.getElementById('score-display').textContent = `Score: ${q.currentScore}`;
  document.getElementById('q-hint').textContent = q.questionText;

  const animEl = document.getElementById('q-anim');
  const choicesEl = document.getElementById('choices');
  choicesEl.innerHTML = '';

  const emoji = getEmoji(q.pixabayKeyword);

  if (currentGame === 'sorting-tray') {
    animEl.innerHTML = `<span class="big-emoji" style="font-size:120px">${emoji}</span>`;
    const hearBtn = document.createElement('button');
    hearBtn.className = 'hear-btn';
    hearBtn.textContent = '🔊 Hear the word!';
    hearBtn.addEventListener('click', () => speakWord(q.correctAnswer));
    choicesEl.appendChild(hearBtn);

    const confirmDiv = document.createElement('div');
    confirmDiv.className = 'parent-confirm';
    confirmDiv.innerHTML = '<div class="confirm-label">Did Nishan say it?</div>';
    const confirmRow = document.createElement('div');
    confirmRow.className = 'confirm-row';

    const yesBtn = document.createElement('button');
    yesBtn.className = 'confirm-btn correct-btn';
    yesBtn.textContent = '✅ Yes!';
    yesBtn.addEventListener('click', () => submitAnswer('correct', yesBtn));

    const noBtn = document.createElement('button');
    noBtn.className = 'confirm-btn wrong-btn';
    noBtn.textContent = '❌ Not yet';
    noBtn.addEventListener('click', () => submitAnswer('wrong', noBtn));

    confirmRow.appendChild(yesBtn);
    confirmRow.appendChild(noBtn);
    confirmDiv.appendChild(confirmRow);
    choicesEl.appendChild(confirmDiv);
    return;
  }

  if (currentGame === 'sound-safari') {
    animEl.innerHTML = `
      <span class="big-emoji">${emoji}</span>
      <span class="anim-letter">${q.correctAnswer.toUpperCase()}</span>`;
    setTimeout(() => speakWord(q.correctAnswer), 600);
  } else if (currentGame === 'berry-basket') {
    const count = parseInt(q.correctAnswer);
    let emojis = '';
    for (let i = 0; i < count; i++) emojis += `<span class="count-emoji">${emoji}</span>`;
    animEl.innerHTML = `<div class="emoji-count-row">${emojis}</div><div class="count-number">${count}</div>`;
  } else if (currentGame === 'shape-village') {
    const svg = SHAPE_SVGS[q.correctAnswer.toLowerCase()] || SHAPE_SVGS['circle'];
    animEl.innerHTML = `<div class="anim-shape">${svg}</div>`;
  }

  q.choices.forEach(choice => {
    const btn = document.createElement('button');
    btn.className = 'choice-btn';
    if (currentGame === 'shape-village' && SHAPE_SVGS[choice.toLowerCase()]) {
      btn.innerHTML = `<div class="choice-shape">${SHAPE_SVGS[choice.toLowerCase()]}</div><div class="choice-label">${choice.toUpperCase()}</div>`;
    } else {
      btn.textContent = choice.toUpperCase();
    }
    btn.addEventListener('click', () => submitAnswer(choice, btn));
    choicesEl.appendChild(btn);
  });
}

function speakWord(word) {
  try {
    window.speechSynthesis.cancel();
    const u = new SpeechSynthesisUtterance(word);
    u.rate = 0.6; u.pitch = 1.5; u.volume = 1;
    const voices = window.speechSynthesis.getVoices();
    const kidVoice = voices.find(v =>
      v.name.includes('Samantha') || v.name.includes('Karen') ||
      v.name.includes('Moira') || v.lang === 'en-US');
    if (kidVoice) u.voice = kidVoice;
    window.speechSynthesis.speak(u);
  } catch(e) {}
}

async function submitAnswer(given, btn) {
  if (answered) return;
  answered = true;
  const isCorrect = currentGame === 'sorting-tray'
    ? given === 'correct'
    : given.toLowerCase() === currentQuestion.correctAnswer.toLowerCase();
  if (currentGame !== 'sorting-tray') {
    btn.classList.add(isCorrect ? 'correct' : 'wrong');
    if (!isCorrect) {
      document.querySelectorAll('.choice-btn').forEach(b => {
        const label = b.querySelector('.choice-label');
        const match = label
          ? label.textContent.toLowerCase() === currentQuestion.correctAnswer.toLowerCase()
          : b.textContent.toLowerCase() === currentQuestion.correctAnswer.toLowerCase();
        if (match) b.classList.add('correct');
      });
    }
  }
  playSound(isCorrect);
  const fb = document.getElementById('feedback');
  fb.textContent = isCorrect ? '🌟 Correct! Great job, Nishan!' : '💪 Keep practicing!';
  fb.style.display = 'block';
  try {
    await fetch(`${API}/game/answer`, {
      method: 'POST', headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        sessionId: currentSession.id, contentId: currentQuestion.contentId,
        childId: CHILD_ID, givenAnswer: given, responseMs: 1000
      })
    });
  } catch(e) {}
  setTimeout(() => {
    if (questionNumber >= currentQuestion.totalQuestions) showComplete();
    else { questionNumber++; loadQuestion(); }
  }, 1800);
}

async function showComplete() {
  document.getElementById('complete-score').textContent =
    `You scored ${currentQuestion.currentScore} points!`;
  playComplete();
  try {
    const res = await fetch(`${API}/activities/recommend/${GAME_TYPE_MAP[currentGame]}`);
    const activity = await res.json();
    document.getElementById('activity-visual').textContent = activity.visualScene || '';
    document.getElementById('activity-emoji').textContent = activity.emoji || '';
    document.getElementById('activity-title').textContent = activity.title || '';
    document.getElementById('activity-desc').textContent = activity.description || '';
    document.getElementById('activity-materials').textContent = activity.materials || '';
    document.getElementById('book-rec1').textContent = activity.bookRec1 || '';
    document.getElementById('book-rec2').textContent = activity.bookRec2 || '';
    document.getElementById('show-rec1').textContent = activity.showRec1 || '';
    document.getElementById('show-rec2').textContent = activity.showRec2 || '';
    document.getElementById('activity-card').style.display = 'block';
  } catch(e) { console.error('Activity fetch failed:', e); }
  showScreen('complete');
}

function playSound(correct) {
  try {
    const ctx = new AudioContext();
    const o = ctx.createOscillator(), g = ctx.createGain();
    o.connect(g); g.connect(ctx.destination);
    g.gain.setValueAtTime(0.3, ctx.currentTime);
    g.gain.exponentialRampToValueAtTime(0.001, ctx.currentTime + 0.3);
    if (correct) {
      o.frequency.setValueAtTime(440, ctx.currentTime);
      o.frequency.rampToValueAtTime(660, ctx.currentTime + 0.15);
    } else { o.frequency.setValueAtTime(220, ctx.currentTime); }
    o.start(); o.stop(ctx.currentTime + 0.3);
  } catch(e) {}
}

function playComplete() {
  try {
    const ctx = new AudioContext();
    [523, 659, 784].forEach((freq, i) => {
      const o = ctx.createOscillator(), g = ctx.createGain();
      o.connect(g); g.connect(ctx.destination);
      g.gain.setValueAtTime(0.3, ctx.currentTime + i * 0.15);
      g.gain.exponentialRampToValueAtTime(0.001, ctx.currentTime + i * 0.15 + 0.3);
      o.frequency.setValueAtTime(freq, ctx.currentTime + i * 0.15);
      o.start(ctx.currentTime + i * 0.15);
      o.stop(ctx.currentTime + i * 0.15 + 0.3);
    });
  } catch(e) {}
}

document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('btn-sound-safari').addEventListener('click', () => startGame('sound-safari'));
  document.getElementById('btn-berry-basket').addEventListener('click', () => startGame('berry-basket'));
  document.getElementById('btn-shape-village').addEventListener('click', () => startGame('shape-village'));
  document.getElementById('btn-sorting-tray').addEventListener('click', () => startGame('sorting-tray'));
  document.getElementById('btn-play-again').addEventListener('click', () => showMenu());
});