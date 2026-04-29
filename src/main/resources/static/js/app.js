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

const ANIMAL_SOUNDS = {
  'cow': 'mooooo', 'dog': 'woof woof', 'cat': 'meow',
  'duck': 'quack quack', 'frog': 'ribbit ribbit',
  'lion': 'roarrr', 'elephant': 'pawww', 'sheep': 'baaaa',
  'horse': 'neigh', 'bird': 'tweet tweet', 'pig': 'oink oink',
  'owl': 'hoo hoo', 'snake': 'hisss', 'monkey': 'ooh ooh aah',
  'bear': 'groarrr', 'whale': 'wooooo', 'fish': 'blub blub',
  'rabbit': 'squeak', 'penguin': 'squawk'
};

const ANIMAL_EMOJIS = {
  'cow': '🐄', 'dog': '🐶', 'cat': '🐱', 'duck': '🦆',
  'frog': '🐸', 'lion': '🦁', 'elephant': '🐘', 'sheep': '🐑',
  'horse': '🐴', 'bird': '🐦', 'pig': '🐷', 'owl': '🦉',
  'snake': '🐍', 'monkey': '🐒', 'bear': '🐻', 'whale': '🐳',
  'fish': '🐟', 'rabbit': '🐰', 'penguin': '🐧'
};

const completedGames = new Set();
let currentDifficulty = 1;

const INTRO_CONTENT = {
  'sound-safari': {
    period1: { visual: '🍎', text: 'This is Apple.', sub: 'Apple starts with the sound... S!' },
    period2: {
      type: 'tap',
      instruction: 'Show me the letter S',
      choices: ['S', 'M', 'A', 'T'],
      correct: 'S',
      svgs: null
    }
  },
  'berry-basket': {
    period1: { visual: '⭐⭐⭐', text: 'This is 3.', sub: 'Count with me: one... two... three!' },
    period2: {
      type: 'parent',
      instruction: 'Show me 3 fingers!',
      sub: 'Hold up 3 fingers and count out loud',
      confirmLabel: 'Did Nishan show 3 fingers?'
    }
  },
  'shape-village': {
    period1: { visual: '⭕', text: 'This is a Circle.', sub: 'Circles are perfectly round with no corners.' },
    period2: {
      type: 'tap',
      instruction: 'Touch the Circle',
      choices: ['circle', 'square', 'triangle'],
      correct: 'circle',
      svgs: {
        'circle': '<svg width="50" height="50" viewBox="0 0 100 100"><circle cx="50" cy="50" r="44" fill="#FF6B6B" stroke="white" stroke-width="3"/></svg>',
        'square': '<svg width="50" height="50" viewBox="0 0 100 100"><rect x="8" y="8" width="84" height="84" rx="14" fill="#4ECDC4" stroke="white" stroke-width="3"/></svg>',
        'triangle': '<svg width="50" height="50" viewBox="0 0 100 100"><polygon points="50,8 92,92 8,92" fill="#FFE66D" stroke="white" stroke-width="3"/></svg>'
      }
    }
  },
  'sorting-tray': {
    period1: { visual: '🐄', text: 'This is a Cow.', sub: 'The cow says... mooooo!' },
    period2: {
      type: 'parent',
      instruction: 'Find something at home that makes a sound!',
      sub: 'Look around the room — what makes noise?',
      confirmLabel: 'Did Nishan find something?'
    }
  }
};

let introPhase = 1;
let pendingGame = null;

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
  'cow': '🐄', 'duck': '🦆', 'butterfly': '🦋', 'strawberry': '🍓',
  'carrot': '🥕', 'shirt': '👕', 'guitar': '🎸', 'hammer': '🔨',
  'bus': '🚌', 'star': '⭐', 'book': '📚', 'cup': '☕',
  'shoe': '👟', 'cake': '🎂', 'boat': '⛵', 'frog': '🐸',
  'bear': '🐻', 'horse': '🐴', 'train': '🚂', 'plane': '✈️',
  'pizza': '🍕', 'banana': '🍌', 'milk': '🥛', 'sock': '🧦',
  'key': '🔑', 'sheep': '🐑', 'pig': '🐷', 'owl': '🦉',
  'snake': '🐍', 'monkey': '🐒',
  'heart shape': '❤️', 'star shape': '⭐', 'cross shape': '✚',
  'crescent shape': '🌙', 'arrow shape': '➡️'
};

function getEmoji(keyword) {
  return ANIMAL_EMOJIS[keyword] || EMOJI_MAP[keyword] || EMOJI_MAP[keyword.split(' ')[0]] || '🎯';
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
  arrow:     '<svg width="100" height="100" viewBox="0 0 100 100"><polygon points="10,38 60,38 60,20 95,50 60,80 60,62 10,62" fill="#34D399" stroke="white" stroke-width="3" stroke-linejoin="round"/></svg>'
};

let currentGame = null, currentSession = null, currentQuestion = null;
let questionNumber = 1, answered = false;

function showScreen(id) {
  document.querySelectorAll('.screen').forEach(function(s) { s.classList.remove('active'); });
  document.getElementById('screen-' + id).classList.add('active');
}

function showMenu() { // goes straight to shelf after first visit
  showScreen('menu');
  currentSession = null; questionNumber = 1; answered = false;
  document.getElementById('activity-card').style.display = 'none';
  document.getElementById('work-cycle-badge').style.display = 'none';
  renderPinkTower(currentDifficulty);
}

function renderPinkTower(level) {
  var tower = document.getElementById('pink-tower');
  var label = document.getElementById('tower-label');
  if (!tower) return;
  tower.innerHTML = '';
  for (var i = level; i >= 1; i--) {
    var block = document.createElement('div');
    block.className = 'tower-block';
    block.style.width = (24 + i * 14) + 'px';
    tower.appendChild(block);
  }
  if (label) label.textContent = 'Level ' + level;
}

function markGameComplete(gameType) {
  completedGames.add(gameType);
  var dotMap = {
    'sound-safari': 'cycle-sound-safari',
    'berry-basket': 'cycle-berry-basket',
    'shape-village': 'cycle-shape-village',
    'sorting-tray': 'cycle-sorting-tray'
  };
  var dot = document.getElementById(dotMap[gameType]);
  if (dot) dot.classList.add('done');
}

function showIntro(gameType) {
  pendingGame = gameType;
  introPhase = 1;
  var intro = INTRO_CONTENT[gameType];
  var bar = document.getElementById('intro-bar');
  bar.style.background = GAME_COLORS[gameType];
  document.getElementById('intro-title').textContent = GAME_NAMES[gameType];
  renderIntroPeriod1(intro);
  showScreen('intro');
}

function renderIntroPeriod1(intro) {
  document.getElementById('period-label').textContent = 'Period 1 — This is...';
  document.getElementById('intro-visual').textContent = intro.period1.visual;
  document.getElementById('intro-text').textContent = intro.period1.text;
  document.getElementById('intro-sub').textContent = intro.period1.sub;
  document.getElementById('intro-choices').innerHTML = '';
  document.getElementById('intro-choices').style.display = 'none';
  var nextBtn = document.getElementById('intro-next-btn');
  nextBtn.textContent = 'I see it! ✋';
  nextBtn.style.display = 'block';
}

function renderIntroPeriod2(intro) {
  if (!intro.period2) { startActualGame(pendingGame); return; }
  introPhase = 2;
  document.getElementById('period-label').textContent = 'Period 2 — Show me...';
  document.getElementById('intro-visual').textContent = '';
  document.getElementById('intro-text').textContent = intro.period2.instruction;
  document.getElementById('intro-sub').textContent = intro.period2.sub || '';
  document.getElementById('intro-next-btn').style.display = 'none';

  var choicesEl = document.getElementById('intro-choices');
  choicesEl.innerHTML = '';

  // ── PARENT CONFIRM type (Berry Basket, Sorting Tray) ──────────────────────
  if (intro.period2.type === 'parent') {
    choicesEl.style.display = 'block';
    var confirmDiv = document.createElement('div');
    confirmDiv.className = 'parent-confirm';
    confirmDiv.innerHTML = '<div class="confirm-label" style="color:#4A2800">' + intro.period2.confirmLabel + '</div>';
    var confirmRow = document.createElement('div');
    confirmRow.className = 'confirm-row';

    var yesBtn = document.createElement('button');
    yesBtn.className = 'intro-choice-btn';
    yesBtn.style.background = '#C8F0D0';
    yesBtn.style.borderColor = '#007A3A';
    yesBtn.textContent = '✅ Yes!';
    yesBtn.addEventListener('click', function() {
      playChime();
      setTimeout(function() { startActualGame(pendingGame); }, 600);
    });

    var notYetBtn = document.createElement('button');
    notYetBtn.className = 'intro-choice-btn';
    notYetBtn.textContent = '🔄 Let\'s try again';
    notYetBtn.addEventListener('click', function() {
      renderIntroPeriod2(intro);
    });

    confirmRow.appendChild(yesBtn);
    confirmRow.appendChild(notYetBtn);
    confirmDiv.appendChild(confirmRow);
    choicesEl.appendChild(confirmDiv);
    return;
  }

  // ── TAP type (Sound Safari, Shape Village) ────────────────────────────────
  choicesEl.style.display = 'grid';

  intro.period2.choices.forEach(function(choice) {
    var btn = document.createElement('button');
    btn.className = 'intro-choice-btn';
    if (intro.period2.svgs && intro.period2.svgs[choice]) {
      btn.innerHTML = intro.period2.svgs[choice] +
        '<div style="font-size:11px;font-weight:900;margin-top:4px;color:#4A2800">' +
        choice.toUpperCase() + '</div>';
    } else {
      btn.textContent = choice.toUpperCase();
    }
    btn.addEventListener('click', function() {
      var isCorrect = choice.toLowerCase() === intro.period2.correct.toLowerCase();
      if (isCorrect) {
        btn.style.background = '#C8F0D0';
        btn.style.borderColor = '#007A3A';
        playChime();
        setTimeout(function() { startActualGame(pendingGame); }, 800);
      } else {
        // No wrong feedback — just glow the correct one and try again
        btn.style.background = '#FFF0D0';
        btn.style.borderColor = '#C8A060';
        setTimeout(function() { renderIntroPeriod2(intro); }, 1000);
      }
    });
    choicesEl.appendChild(btn);
  });
}

function playChime() {
  try {
    var ctx = new AudioContext();
    var o = ctx.createOscillator();
    var g = ctx.createGain();
    o.connect(g); g.connect(ctx.destination);
    o.type = 'sine';
    o.frequency.setValueAtTime(784, ctx.currentTime);
    o.frequency.rampToValueAtTime(1047, ctx.currentTime + 0.2);
    g.gain.setValueAtTime(0.3, ctx.currentTime);
    g.gain.exponentialRampToValueAtTime(0.001, ctx.currentTime + 0.5);
    o.start(); o.stop(ctx.currentTime + 0.5);
  } catch(e) {}
}

function selectWork(gameType) { showIntro(gameType); }

async function startActualGame(gameType) {
  currentGame = gameType; questionNumber = 1; answered = false;
  var bar = document.getElementById('game-bar');
  var body = document.getElementById('game-body');
  bar.style.background = GAME_COLORS[gameType];
  body.style.background = GAME_COLORS[gameType];
  document.getElementById('game-title').textContent = GAME_NAMES[gameType];
  try {
    var res = await fetch(API + '/game/' + gameType + '/start/' + CHILD_ID, { method: 'POST' });
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
    var res = await fetch(API + '/game/' + currentGame + '/question/' + currentSession.id + '/' + questionNumber);
    currentQuestion = await res.json();
    renderQuestion(currentQuestion);
  } catch(e) { console.error('Failed to load:', e); }
}

function playCountSounds(count) {
  var notes = [523, 587, 659, 698, 784, 880, 988, 1047, 1175, 1319,
               1397, 1568, 1760, 1976, 2093, 2349, 2637, 2794, 3136, 3520];
  for (var i = 0; i < count; i++) {
    setTimeout(function(idx) {
      return function() {
        try {
          var ctx = new AudioContext();
          var o = ctx.createOscillator();
          var g = ctx.createGain();
          o.connect(g); g.connect(ctx.destination);
          o.type = 'sine';
          o.frequency.setValueAtTime(notes[idx] || 523, ctx.currentTime);
          g.gain.setValueAtTime(0.4, ctx.currentTime);
          g.gain.exponentialRampToValueAtTime(0.001, ctx.currentTime + 0.25);
          o.start(); o.stop(ctx.currentTime + 0.25);
        } catch(e) {}
      };
    }(i), i * 300);
  }
}

function speakAnimalSound(animalKey, animalName, sound) {
  var extensions = ['mp3', 'wav'];
  var played = false;

  function tryPlay(idx) {
    if (idx >= extensions.length) {
      try {
        window.speechSynthesis.cancel();
        var voices = window.speechSynthesis.getVoices();
        var kidVoice = voices.find(function(v) {
          return v.name.includes('Samantha') || v.name.includes('Karen') ||
                 v.name.includes('Moira') || v.lang === 'en-US';
        });
        var phrase = sound ? 'The ' + animalName + ' says ' + sound : animalName;
        var u = new SpeechSynthesisUtterance(phrase);
        u.rate = 0.55; u.pitch = 1.3; u.volume = 1;
        if (kidVoice) u.voice = kidVoice;
        window.speechSynthesis.speak(u);
      } catch(e) {}
      return;
    }
    var audio = new Audio('/audio/' + animalKey + '.' + extensions[idx]);
    audio.oncanplaythrough = function() {
      if (!played) { played = true; audio.play(); }
    };
    audio.onerror = function() { tryPlay(idx + 1); };
    audio.load();
  }

  tryPlay(0);
}

function speakWord(word) {
  try {
    window.speechSynthesis.cancel();
    var u = new SpeechSynthesisUtterance(word);
    u.rate = 0.6; u.pitch = 1.5; u.volume = 1;
    var voices = window.speechSynthesis.getVoices();
    var kidVoice = voices.find(function(v) {
      return v.name.includes('Samantha') || v.name.includes('Karen') ||
             v.name.includes('Moira') || v.lang === 'en-US';
    });
    if (kidVoice) u.voice = kidVoice;
    window.speechSynthesis.speak(u);
  } catch(e) {}
}

function renderQuestion(q) {
  var pct = ((questionNumber - 1) / q.totalQuestions) * 100;
  document.getElementById('prog-fill').style.width = pct + '%';
  document.getElementById('q-count').textContent = 'Question ' + questionNumber + ' of ' + q.totalQuestions;
  document.getElementById('score-display').textContent = 'Score: ' + q.currentScore;
  document.getElementById('q-hint').textContent = q.questionText;

  var animEl = document.getElementById('q-anim');
  var choicesEl = document.getElementById('choices');
  choicesEl.innerHTML = '';

  var animalKey = q.pixabayKeyword.toLowerCase();
  var animalSound = ANIMAL_SOUNDS[animalKey];

  if (currentGame === 'sorting-tray') {
    var emoji = ANIMAL_EMOJIS[animalKey] || getEmoji(q.pixabayKeyword);
    animEl.innerHTML = '<span class="big-emoji" style="font-size:110px">' + emoji + '</span>';
    document.getElementById('q-hint').textContent = 'What sound does the ' + q.correctAnswer + ' make?';

    var hearBtn = document.createElement('button');
    hearBtn.className = 'hear-btn';
    hearBtn.textContent = '🔊 Hear the sound!';
    hearBtn.addEventListener('click', function() { speakAnimalSound(animalKey, q.correctAnswer, animalSound); });
    choicesEl.appendChild(hearBtn);

    var confirmDiv = document.createElement('div');
    confirmDiv.className = 'parent-confirm';
    confirmDiv.innerHTML = '<div class="confirm-label">Did Nishan make the sound?</div>';
    var confirmRow = document.createElement('div');
    confirmRow.className = 'confirm-row';

    var yesBtn = document.createElement('button');
    yesBtn.className = 'confirm-btn correct-btn';
    yesBtn.textContent = '✅ Yes!';
    yesBtn.addEventListener('click', function() { submitAnswer('correct', yesBtn); });

    var noBtn = document.createElement('button');
    noBtn.className = 'confirm-btn wrong-btn';
    noBtn.textContent = '❌ Not yet';
    noBtn.addEventListener('click', function() { submitAnswer('wrong', noBtn); });

    confirmRow.appendChild(yesBtn);
    confirmRow.appendChild(noBtn);
    confirmDiv.appendChild(confirmRow);
    choicesEl.appendChild(confirmDiv);
    return;
  }

  if (currentGame === 'sound-safari') {
    var sfEmoji = getEmoji(q.pixabayKeyword);
    animEl.innerHTML = '<span class="big-emoji">' + sfEmoji + '</span><span class="anim-letter">' + q.correctAnswer.toUpperCase() + '</span>';
    setTimeout(function() { playLetterSound(q.correctAnswer); }, 600);
  } else if (currentGame === 'berry-basket') {
    var bbEmoji = getEmoji(q.pixabayKeyword);
    var count = parseInt(q.correctAnswer);
    var emojis = '';
    for (var i = 0; i < count; i++) emojis += '<span class="count-emoji">' + bbEmoji + '</span>';
    animEl.innerHTML = '<div class="emoji-count-row">' + emojis + '</div><div class="count-number">' + count + '</div>';
    setTimeout(function() { playCountSounds(count); }, 400);
  } else if (currentGame === 'shape-village') {
    var svg = SHAPE_SVGS[q.correctAnswer.toLowerCase()] || SHAPE_SVGS['circle'];
    animEl.innerHTML = '<div class="anim-shape">' + svg + '</div>';
  }

  q.choices.forEach(function(choice) {
    var btn = document.createElement('button');
    btn.className = 'choice-btn';
    if (currentGame === 'shape-village' && SHAPE_SVGS[choice.toLowerCase()]) {
      btn.innerHTML = '<div class="choice-shape">' + SHAPE_SVGS[choice.toLowerCase()] + '</div><div class="choice-label">' + choice.toUpperCase() + '</div>';
    } else {
      btn.textContent = choice.toUpperCase();
    }
    btn.addEventListener('click', function() { submitAnswer(choice, btn); });
    choicesEl.appendChild(btn);
  });
}

async function submitAnswer(given, btn) {
  if (answered) return;
  answered = true;
  var isCorrect = currentGame === 'sorting-tray'
    ? given === 'correct'
    : given.toLowerCase() === currentQuestion.correctAnswer.toLowerCase();
  if (currentGame !== 'sorting-tray') {
    btn.classList.add(isCorrect ? 'correct' : 'wrong');
    if (!isCorrect) {
      document.querySelectorAll('.choice-btn').forEach(function(b) {
        var label = b.querySelector('.choice-label');
        var match = label
          ? label.textContent.toLowerCase() === currentQuestion.correctAnswer.toLowerCase()
          : b.textContent.toLowerCase() === currentQuestion.correctAnswer.toLowerCase();
        if (match) b.classList.add('correct');
      });
    }
  }
  playSound(isCorrect);
  var fb = document.getElementById('feedback');
  fb.textContent = isCorrect ? '🌟 Correct! Great job, Nishan!' : '💪 Keep practicing!';
  fb.style.display = 'block';
  try {
    await fetch(API + '/game/answer', {
      method: 'POST', headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        sessionId: currentSession.id, contentId: currentQuestion.contentId,
        childId: CHILD_ID, givenAnswer: given, responseMs: 1000
      })
    });
  } catch(e) {}
  setTimeout(function() {
    if (questionNumber >= currentQuestion.totalQuestions) showComplete();
    else { questionNumber++; loadQuestion(); }
  }, 1800);
}

async function showComplete() {
  document.getElementById('complete-score').textContent = 'You scored ' + currentQuestion.currentScore + ' points!';
  playComplete();
  setTimeout(playYay, 300);
  markGameComplete(currentGame);
  if (completedGames.size === 4) {
    document.getElementById('work-cycle-badge').style.display = 'block';
  }
  try {
    var res = await fetch(API + '/activities/recommend/' + GAME_TYPE_MAP[currentGame]);
    var activity = await res.json();
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
  } catch(e) {}
  showScreen('complete');
}

function playSound(correct) {
  try {
    var ctx = new AudioContext();
    var o = ctx.createOscillator(), g = ctx.createGain();
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
    var ctx = new AudioContext();
    [523, 659, 784].forEach(function(freq, i) {
      var o = ctx.createOscillator(), g = ctx.createGain();
      o.connect(g); g.connect(ctx.destination);
      g.gain.setValueAtTime(0.3, ctx.currentTime + i * 0.15);
      g.gain.exponentialRampToValueAtTime(0.001, ctx.currentTime + i * 0.15 + 0.3);
      o.frequency.setValueAtTime(freq, ctx.currentTime + i * 0.15);
      o.start(ctx.currentTime + i * 0.15);
      o.stop(ctx.currentTime + i * 0.15 + 0.3);
    });
  } catch(e) {}
}

function playYay() {
  try {
    window.speechSynthesis.cancel();
    var voices = window.speechSynthesis.getVoices();
    var kidVoice = voices.find(function(v) {
      return v.name.includes('Samantha') || v.name.includes('Karen') ||
             v.name.includes('Moira') || v.lang === 'en-US';
    });
    var phrases = ['Yaaay!', 'Woohoo!', 'Amazing!', 'You did it!', 'Fantastic!'];
    var phrase = phrases[Math.floor(Math.random() * phrases.length)];
    var u = new SpeechSynthesisUtterance(phrase);
    u.rate = 0.8; u.pitch = 2.0; u.volume = 1;
    if (kidVoice) u.voice = kidVoice;
    window.speechSynthesis.speak(u);
  } catch(e) {}
}

function introNextClicked() {
  var intro = INTRO_CONTENT[pendingGame];
  if (introPhase === 1) renderIntroPeriod2(intro);
  else startActualGame(pendingGame);
}

document.addEventListener('DOMContentLoaded', function() {
  renderPinkTower(currentDifficulty);
});

// ── PARENT DASHBOARD ────────────────────────────────────────────────────────
var logoTapCount = 0;
var logoTapTimer = null;
var pinEntry = '';
var PIN = '1234';

var sproutLogo = document.querySelector('.sprout-logo');
if (sproutLogo) {
  sproutLogo.addEventListener('click', function() {
    logoTapCount++;
    if (logoTapTimer) clearTimeout(logoTapTimer);
    logoTapTimer = setTimeout(function() { logoTapCount = 0; }, 1500);
    if (logoTapCount >= 3) {
      logoTapCount = 0;
      showPinOverlay();
    }
  });
}

function showPinOverlay() {
  pinEntry = '';
  updatePinDots();
  document.getElementById('pin-error').style.display = 'none';
  document.getElementById('pin-overlay').style.display = 'flex';
}

function pinPress(digit) {
  if (pinEntry.length >= 4) return;
  pinEntry += digit;
  updatePinDots();
  if (pinEntry.length === 4) {
    setTimeout(function() {
      if (pinEntry === PIN) {
        document.getElementById('pin-overlay').style.display = 'none';
        loadParentDashboard();
      } else {
        document.getElementById('pin-error').style.display = 'block';
        pinEntry = '';
        updatePinDots();
      }
    }, 200);
  }
}

function pinClear() {
  pinEntry = '';
  updatePinDots();
  document.getElementById('pin-error').style.display = 'none';
}

function pinCancel() {
  pinEntry = '';
  document.getElementById('pin-overlay').style.display = 'none';
}

function updatePinDots() {
  var dots = document.querySelectorAll('.pin-dot');
  dots.forEach(function(dot, i) {
    dot.classList.toggle('filled', i < pinEntry.length);
  });
}

var GAME_LABELS = {
  'SOUND_SAFARI': '🔤 Sound Safari',
  'BERRY_BASKET': '🔢 Berry Basket',
  'SHAPE_VILLAGE': '🔷 Shape Village',
  'SORTING_TRAY': '🐾 Sorting Tray'
};

async function loadParentDashboard() {
  showScreen('parent');
  document.getElementById('parent-loading').style.display = 'block';
  document.getElementById('parent-loading').textContent = 'Loading...';

  try {
    var res = await fetch(API + '/parent/dashboard/' + CHILD_ID);
    var d = await res.json();
    renderParentDashboard(d);
  } catch(e) {
    document.getElementById('parent-loading').textContent = 'Failed to load. Check connection.';
  }
}

function renderParentDashboard(d) {
  var body = document.getElementById('parent-body');
  var overall = d.totalCorrect + d.totalWrong > 0
    ? Math.round(d.totalCorrect * 100 / (d.totalCorrect + d.totalWrong)) : 0;

  var html = '';

  // Child card
  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">🌱 Nishan\'s Profile</div>';
  html += '<div class="stat-row"><span class="stat-label">Age</span><span class="stat-value">' + d.childAgeMonths + ' months</span></div>';
  html += '<div class="stat-row"><span class="stat-label">Total Sessions</span><span class="stat-value">' + d.totalSessions + '</span></div>';
  html += '<div class="stat-row"><span class="stat-label">Overall Accuracy</span><span class="stat-value">' + overall + '%</span></div>';
  html += '<div class="pct-bar"><div class="pct-fill" style="width:' + overall + '%"></div></div>';
  html += '</div>';

  // ELOF domains
  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">📊 ELOF Domains</div>';
  Object.keys(d.elofDomains).forEach(function(domain) {
    html += '<div class="stat-row"><span class="stat-label">' + domain + '</span><span class="stat-value">' + d.elofDomains[domain] + ' correct</span></div>';
  });
  html += '</div>';

  // Per game stats
  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">🎮 Game Breakdown</div>';
  Object.keys(d.gameStats).forEach(function(key) {
    var g = d.gameStats[key];
    html += '<div style="margin-bottom:10px">';
    html += '<div class="stat-row"><span class="stat-label">' + (GAME_LABELS[key] || key) + '</span><span class="stat-value">' + g.pct + '%</span></div>';
    html += '<div style="font-size:10px;color:#8B6B3A;margin-bottom:3px">' + g.sessions + ' sessions · ' + g.correct + ' correct · ' + g.wrong + ' wrong</div>';
    html += '<div class="pct-bar"><div class="pct-fill" style="width:' + g.pct + '%;background:' + (g.pct >= 80 ? '#00A651' : g.pct >= 50 ? '#F07820' : '#CC1A1A') + '"></div></div>';
    html += '</div>';
  });
  html += '</div>';

  // Top struggles
  if (d.topStruggles && d.topStruggles.length > 0) {
    html += '<div class="stat-card">';
    html += '<div class="stat-card-title">💪 Needs More Practice</div>';
    d.topStruggles.forEach(function(s) {
      var emoji = ANIMAL_EMOJIS[s.emoji] || EMOJI_MAP[s.emoji] || '🎯';
      html += '<div class="struggle-item">';
      html += '<div class="struggle-emoji">' + emoji + '</div>';
      html += '<div class="struggle-label">' + s.contentValue + ' <span style="color:#8B6B3A;font-size:10px">(' + s.contentType + ')</span></div>';
      html += '<div class="struggle-count">' + s.wrongCount + ' misses</div>';
      html += '</div>';
    });
    html += '</div>';
  }

  // Recent sessions
  if (d.recentSessions && d.recentSessions.length > 0) {
    html += '<div class="stat-card">';
    html += '<div class="stat-card-title">🕐 Recent Sessions</div>';
    d.recentSessions.forEach(function(s) {
      html += '<div class="session-row">';
      html += '<span style="font-weight:700;color:#4A2800">' + (GAME_LABELS[s.gameType] || s.gameType) + '</span>';
      html += '<span style="color:#8B6B3A">' + s.date + '</span>';
      html += '<span style="font-weight:900;color:#CC1A1A">' + s.score + ' pts</span>';
      html += '</div>';
    });
    html += '</div>';
  }

  document.getElementById('parent-loading').style.display = 'none';
  body.innerHTML = html;
}

// ── SPLASH SCREEN ────────────────────────────────────────────────────────
var FLOATIES = [
  'A','B','C','D','S','M','T',
  '1','2','3','4','5',
  '⭕','🔷','⭐','🔺',
  '🐄','🐶','🐱','🐸','🦁','🐘'
];

function initSplash() {
  var container = document.getElementById('splash-floaties');
  if (!container) return;
  for (var i = 0; i < 18; i++) {
    var el = document.createElement('div');
    el.className = 'floatie';
    el.textContent = FLOATIES[Math.floor(Math.random() * FLOATIES.length)];
    el.style.left = (Math.random() * 90) + '%';
    el.style.animationDuration = (6 + Math.random() * 8) + 's';
    el.style.animationDelay = (Math.random() * 8) + 's';
    el.style.fontSize = (20 + Math.random() * 20) + 'px';
    container.appendChild(el);
  }
}

function showDedication() {
  showScreen('dedication');
}

// ── PARENT DASHBOARD ─────────────────────────────────────────────────────
var logoTapCount = 0;
var logoTapTimer = null;
var pinEntry = '';
var PIN = '1234';

function initLogoTap() {
  var logo = document.getElementById('sprout-logo-tap');
  if (!logo) return;
  logo.addEventListener('click', function() {
    logoTapCount++;
    if (logoTapTimer) clearTimeout(logoTapTimer);
    logoTapTimer = setTimeout(function() { logoTapCount = 0; }, 1500);
    if (logoTapCount >= 3) {
      logoTapCount = 0;
      showPinOverlay();
    }
  });
}

function showPinOverlay() {
  pinEntry = '';
  updatePinDots();
  document.getElementById('pin-error').style.display = 'none';
  document.getElementById('pin-overlay').style.display = 'flex';
}

function pinPress(digit) {
  if (pinEntry.length >= 4) return;
  pinEntry += digit;
  updatePinDots();
  if (pinEntry.length === 4) {
    setTimeout(function() {
      if (pinEntry === PIN) {
        document.getElementById('pin-overlay').style.display = 'none';
        loadParentDashboard();
      } else {
        document.getElementById('pin-error').style.display = 'block';
        pinEntry = '';
        updatePinDots();
      }
    }, 200);
  }
}

function pinClear() {
  pinEntry = '';
  updatePinDots();
  document.getElementById('pin-error').style.display = 'none';
}

function pinCancel() {
  pinEntry = '';
  document.getElementById('pin-overlay').style.display = 'none';
}

function updatePinDots() {
  var dots = document.querySelectorAll('.pin-dot');
  dots.forEach(function(dot, i) {
    dot.classList.toggle('filled', i < pinEntry.length);
  });
}

var GAME_LABELS = {
  'SOUND_SAFARI': '🔤 Sound Safari',
  'BERRY_BASKET': '🔢 Berry Basket',
  'SHAPE_VILLAGE': '🔷 Shape Village',
  'SORTING_TRAY': '🐾 Sorting Tray'
};

async function loadParentDashboard() {
  showScreen('parent');
  document.getElementById('parent-loading').style.display = 'block';
  document.getElementById('parent-loading').textContent = 'Loading...';
  try {
    var res = await fetch(API + '/parent/dashboard/' + CHILD_ID);
    var d = await res.json();
    renderParentDashboard(d);
  } catch(e) {
    document.getElementById('parent-loading').textContent = 'Failed to load.';
  }
}

function renderParentDashboard(d) {
  var overall = d.totalCorrect + d.totalWrong > 0
    ? Math.round(d.totalCorrect * 100 / (d.totalCorrect + d.totalWrong)) : 0;

  var html = '';

  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">🌱 Nishan\'s Profile</div>';
  html += '<div class="stat-row"><span class="stat-label">Age</span><span class="stat-value">' + d.childAgeMonths + ' months</span></div>';
  html += '<div class="stat-row"><span class="stat-label">Total Sessions</span><span class="stat-value">' + d.totalSessions + '</span></div>';
  html += '<div class="stat-row"><span class="stat-label">Overall Accuracy</span><span class="stat-value">' + overall + '%</span></div>';
  html += '<div class="pct-bar"><div class="pct-fill" style="width:' + overall + '%"></div></div>';
  html += '</div>';

  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">📊 ELOF Domains</div>';
  Object.keys(d.elofDomains).forEach(function(domain) {
    html += '<div class="stat-row"><span class="stat-label">' + domain + '</span><span class="stat-value">' + d.elofDomains[domain] + ' correct</span></div>';
  });
  html += '</div>';

  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">🎮 Game Breakdown</div>';
  Object.keys(d.gameStats).forEach(function(key) {
    var g = d.gameStats[key];
    html += '<div style="margin-bottom:10px">';
    html += '<div class="stat-row"><span class="stat-label">' + (GAME_LABELS[key] || key) + '</span><span class="stat-value">' + g.pct + '%</span></div>';
    html += '<div style="font-size:10px;color:#8B6B3A;margin-bottom:3px">' + g.sessions + ' sessions · ' + g.correct + ' correct · ' + g.wrong + ' wrong</div>';
    html += '<div class="pct-bar"><div class="pct-fill" style="width:' + g.pct + '%;background:' + (g.pct >= 80 ? '#00A651' : g.pct >= 50 ? '#F07820' : '#CC1A1A') + '"></div></div>';
    html += '</div>';
  });
  html += '</div>';

  if (d.topStruggles && d.topStruggles.length > 0) {
    html += '<div class="stat-card">';
    html += '<div class="stat-card-title">💪 Needs More Practice</div>';
    d.topStruggles.forEach(function(s) {
      var emoji = ANIMAL_EMOJIS[s.emoji] || EMOJI_MAP[s.emoji] || '🎯';
      html += '<div class="struggle-item">';
      html += '<div class="struggle-emoji">' + emoji + '</div>';
      html += '<div class="struggle-label">' + s.contentValue + ' <span style="color:#8B6B3A;font-size:10px">(' + s.contentType + ')</span></div>';
      html += '<div class="struggle-count">' + s.wrongCount + ' misses</div>';
      html += '</div>';
    });
    html += '</div>';
  }

  if (d.recentSessions && d.recentSessions.length > 0) {
    html += '<div class="stat-card">';
    html += '<div class="stat-card-title">🕐 Recent Sessions</div>';
    d.recentSessions.forEach(function(s) {
      html += '<div class="session-row">';
      html += '<span style="font-weight:700;color:#4A2800">' + (GAME_LABELS[s.gameType] || s.gameType) + '</span>';
      html += '<span style="color:#8B6B3A">' + s.date + '</span>';
      html += '<span style="font-weight:900;color:#CC1A1A">' + s.score + ' pts</span>';
      html += '</div>';
    });
    html += '</div>';
  }

  document.getElementById('parent-loading').style.display = 'none';
  document.getElementById('parent-body').innerHTML = html;
}

// ── INIT ─────────────────────────────────────────────────────────────────
document.addEventListener('DOMContentLoaded', function() {
  renderPinkTower(currentDifficulty);
  initSplash();
  initLogoTap();
});

// ── SPLASH SCREEN ────────────────────────────────────────────────────────
var FLOATIES = [
  'A','B','C','D','S','M','T',
  '1','2','3','4','5',
  '⭕','🔷','⭐','🔺',
  '🐄','🐶','🐱','🐸','🦁','🐘'
];

function initSplash() {
  var container = document.getElementById('splash-floaties');
  if (!container) return;
  for (var i = 0; i < 18; i++) {
    var el = document.createElement('div');
    el.className = 'floatie';
    el.textContent = FLOATIES[Math.floor(Math.random() * FLOATIES.length)];
    el.style.left = (Math.random() * 90) + '%';
    el.style.animationDuration = (6 + Math.random() * 8) + 's';
    el.style.animationDelay = (Math.random() * 8) + 's';
    el.style.fontSize = (20 + Math.random() * 20) + 'px';
    container.appendChild(el);
  }
}

function showDedication() {
  showScreen('dedication');
}

// ── PARENT DASHBOARD ─────────────────────────────────────────────────────
var logoTapCount = 0;
var logoTapTimer = null;
var pinEntry = '';
var PIN = '1234';

function initLogoTap() {
  var logo = document.getElementById('sprout-logo-tap');
  if (!logo) return;
  logo.addEventListener('click', function() {
    logoTapCount++;
    if (logoTapTimer) clearTimeout(logoTapTimer);
    logoTapTimer = setTimeout(function() { logoTapCount = 0; }, 1500);
    if (logoTapCount >= 3) {
      logoTapCount = 0;
      showPinOverlay();
    }
  });
}

function showPinOverlay() {
  pinEntry = '';
  updatePinDots();
  document.getElementById('pin-error').style.display = 'none';
  document.getElementById('pin-overlay').style.display = 'flex';
}

function pinPress(digit) {
  if (pinEntry.length >= 4) return;
  pinEntry += digit;
  updatePinDots();
  if (pinEntry.length === 4) {
    setTimeout(function() {
      if (pinEntry === PIN) {
        document.getElementById('pin-overlay').style.display = 'none';
        loadParentDashboard();
      } else {
        document.getElementById('pin-error').style.display = 'block';
        pinEntry = '';
        updatePinDots();
      }
    }, 200);
  }
}

function pinClear() {
  pinEntry = '';
  updatePinDots();
  document.getElementById('pin-error').style.display = 'none';
}

function pinCancel() {
  pinEntry = '';
  document.getElementById('pin-overlay').style.display = 'none';
}

function updatePinDots() {
  var dots = document.querySelectorAll('.pin-dot');
  dots.forEach(function(dot, i) {
    dot.classList.toggle('filled', i < pinEntry.length);
  });
}

var GAME_LABELS = {
  'SOUND_SAFARI': '🔤 Sound Safari',
  'BERRY_BASKET': '🔢 Berry Basket',
  'SHAPE_VILLAGE': '🔷 Shape Village',
  'SORTING_TRAY': '🐾 Sorting Tray'
};

async function loadParentDashboard() {
  showScreen('parent');
  document.getElementById('parent-loading').style.display = 'block';
  document.getElementById('parent-loading').textContent = 'Loading...';
  try {
    var res = await fetch(API + '/parent/dashboard/' + CHILD_ID);
    var d = await res.json();
    renderParentDashboard(d);
  } catch(e) {
    document.getElementById('parent-loading').textContent = 'Failed to load.';
  }
}

function renderParentDashboard(d) {
  var overall = d.totalCorrect + d.totalWrong > 0
    ? Math.round(d.totalCorrect * 100 / (d.totalCorrect + d.totalWrong)) : 0;

  var html = '';

  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">🌱 Nishan\'s Profile</div>';
  html += '<div class="stat-row"><span class="stat-label">Age</span><span class="stat-value">' + d.childAgeMonths + ' months</span></div>';
  html += '<div class="stat-row"><span class="stat-label">Total Sessions</span><span class="stat-value">' + d.totalSessions + '</span></div>';
  html += '<div class="stat-row"><span class="stat-label">Overall Accuracy</span><span class="stat-value">' + overall + '%</span></div>';
  html += '<div class="pct-bar"><div class="pct-fill" style="width:' + overall + '%"></div></div>';
  html += '</div>';

  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">📊 ELOF Domains</div>';
  Object.keys(d.elofDomains).forEach(function(domain) {
    html += '<div class="stat-row"><span class="stat-label">' + domain + '</span><span class="stat-value">' + d.elofDomains[domain] + ' correct</span></div>';
  });
  html += '</div>';

  html += '<div class="stat-card">';
  html += '<div class="stat-card-title">🎮 Game Breakdown</div>';
  Object.keys(d.gameStats).forEach(function(key) {
    var g = d.gameStats[key];
    html += '<div style="margin-bottom:10px">';
    html += '<div class="stat-row"><span class="stat-label">' + (GAME_LABELS[key] || key) + '</span><span class="stat-value">' + g.pct + '%</span></div>';
    html += '<div style="font-size:10px;color:#8B6B3A;margin-bottom:3px">' + g.sessions + ' sessions · ' + g.correct + ' correct · ' + g.wrong + ' wrong</div>';
    html += '<div class="pct-bar"><div class="pct-fill" style="width:' + g.pct + '%;background:' + (g.pct >= 80 ? '#00A651' : g.pct >= 50 ? '#F07820' : '#CC1A1A') + '"></div></div>';
    html += '</div>';
  });
  html += '</div>';

  if (d.topStruggles && d.topStruggles.length > 0) {
    html += '<div class="stat-card">';
    html += '<div class="stat-card-title">💪 Needs More Practice</div>';
    d.topStruggles.forEach(function(s) {
      var emoji = ANIMAL_EMOJIS[s.emoji] || EMOJI_MAP[s.emoji] || '🎯';
      html += '<div class="struggle-item">';
      html += '<div class="struggle-emoji">' + emoji + '</div>';
      html += '<div class="struggle-label">' + s.contentValue + ' <span style="color:#8B6B3A;font-size:10px">(' + s.contentType + ')</span></div>';
      html += '<div class="struggle-count">' + s.wrongCount + ' misses</div>';
      html += '</div>';
    });
    html += '</div>';
  }

  if (d.recentSessions && d.recentSessions.length > 0) {
    html += '<div class="stat-card">';
    html += '<div class="stat-card-title">🕐 Recent Sessions</div>';
    d.recentSessions.forEach(function(s) {
      html += '<div class="session-row">';
      html += '<span style="font-weight:700;color:#4A2800">' + (GAME_LABELS[s.gameType] || s.gameType) + '</span>';
      html += '<span style="color:#8B6B3A">' + s.date + '</span>';
      html += '<span style="font-weight:900;color:#CC1A1A">' + s.score + ' pts</span>';
      html += '</div>';
    });
    html += '</div>';
  }

  document.getElementById('parent-loading').style.display = 'none';
  document.getElementById('parent-body').innerHTML = html;
}

// ── INIT ─────────────────────────────────────────────────────────────────
document.addEventListener('DOMContentLoaded', function() {
  renderPinkTower(currentDifficulty);
  initSplash();
  initLogoTap();
});

function playLetterSound(letter) {
  var audio = new Audio('/audio/letters/' + letter.toLowerCase() + '.mp3');
  audio.onerror = function() { speakWord(letter); };
  audio.play().catch(function() { speakWord(letter); });
}

// override showMenu to always go straight to shelf
var _originalShowMenu = showMenu;
showMenu = function() {
  showScreen('menu');
  currentSession = null; questionNumber = 1; answered = false;
  var ac = document.getElementById('activity-card');
  if (ac) ac.style.display = 'none';
  var wb = document.getElementById('work-cycle-badge');
  if (wb) wb.style.display = 'none';
  renderPinkTower(currentDifficulty);
};

function showTab(tab) {
  ['rhymes','books','shows'].forEach(function(t) {
    document.getElementById('media-' + t).style.display = t === tab ? 'block' : 'none';
    var btn = document.getElementById('tab-' + t);
    if (btn) btn.classList.toggle('active-tab', t === tab);
  });
}

function showLyrics(title, lyrics) {
  document.getElementById('lyrics-title').textContent = title;
  document.getElementById('lyrics-body').textContent = lyrics;
  document.getElementById('lyrics-modal').style.display = 'flex';
}

var RHYME_LYRICS = {
  'Itsy Bitsy Spider': 'The itsy bitsy spider\nClimbed up the waterspout.\nDown came the rain\nAnd washed the spider out.\nOut came the sun\nAnd dried up all the rain,\nAnd the itsy bitsy spider\nClimbed up the spout again.',
  'Mary Mary Quite Contrary': 'Mary, Mary, quite contrary,\nHow does your garden grow?\nWith silver bells and cockle shells,\nAnd pretty maids all in a row.',
  'One Two Three Four Five': 'One, two, three, four, five,\nOnce I caught a fish alive.\nSix, seven, eight, nine, ten,\nThen I let it go again.\nWhy did you let it go?\nBecause it bit my finger so.\nWhich finger did it bite?\nThis little finger on the right.',
  'This Little Piggy': 'This little piggy went to market,\nThis little piggy stayed home,\nThis little piggy had roast beef,\nThis little piggy had none,\nAnd this little piggy cried\nWee wee wee all the way home!',
  'Five Little Ducks': 'Five little ducks went out one day,\nOver the hill and far away.\nMother duck said quack quack quack quack,\nBut only four little ducks came back.\n\nFour little ducks went out one day,\nOver the hill and far away.\nMother duck said quack quack quack quack,\nBut only three little ducks came back.\n\nOne little duck went out one day,\nOver the hill and far away.\nMother duck said quack quack quack quack,\nAnd all the five little ducks came back!',
  'Old MacDonald Had a Farm': 'Old MacDonald had a farm, E-I-E-I-O!\nAnd on his farm he had a cow, E-I-E-I-O!\nWith a moo moo here, and a moo moo there,\nHere a moo, there a moo, everywhere a moo moo.\nOld MacDonald had a farm, E-I-E-I-O!\n\nAnd on his farm he had a dog, E-I-E-I-O!\nWith a woof woof here, and a woof woof there...\n\nAnd on his farm he had a cat, E-I-E-I-O!\nWith a meow meow here, and a meow meow there...',
  'Baa Baa Black Sheep': 'Baa baa black sheep,\nHave you any wool?\nYes sir, yes sir,\nThree bags full.\nOne for the master,\nOne for the dame,\nAnd one for the little boy\nWho lives down the lane.'
};

function showLyrics(title) {
  var lyrics = RHYME_LYRICS[title] || 'Lyrics coming soon!';
  document.getElementById('lyrics-title').textContent = title;
  document.getElementById('lyrics-body').textContent = lyrics;
  document.getElementById('lyrics-modal').style.display = 'flex';
}

// ── SPROUTROOT LOADER ────────────────────────────────────────────────────
(function() {
  var loader = document.createElement('div');
  loader.className = 'sprout-loader';
  loader.id = 'sprout-loader';
  loader.innerHTML = '<div class="loader-dots"><div class="loader-dot"></div><div class="loader-dot"></div><div class="loader-dot"></div><div class="loader-dot"></div><div class="loader-dot"></div></div><div class="loader-sprout">🌱</div>';
  document.body.appendChild(loader);
})();

var _baseShowScreen = showScreen;
showScreen = function(id) {
  var loader = document.getElementById('sprout-loader');
  loader.classList.add('visible');
  setTimeout(function() {
    _baseShowScreen(id);
    setTimeout(function() {
      loader.classList.remove('visible');
    }, 150);
  }, 400);
};

// fix loader init
document.addEventListener('DOMContentLoaded', function() {
  if (!document.getElementById('sprout-loader')) {
    var loader = document.createElement('div');
    loader.className = 'sprout-loader';
    loader.id = 'sprout-loader';
    loader.innerHTML = '<div class="loader-dots"><div class="loader-dot"></div><div class="loader-dot"></div><div class="loader-dot"></div><div class="loader-dot"></div><div class="loader-dot"></div></div><div class="loader-sprout">🌱</div>';
    document.body.appendChild(loader);
  }
});

// fix loader — wire after DOM ready
document.addEventListener('DOMContentLoaded', function() {
  var loader = document.getElementById('sprout-loader');
  if (!loader) return;
  var _realShowScreen = showScreen;
  showScreen = function(id) {
    loader.classList.add('visible');
    setTimeout(function() {
      _realShowScreen(id);
      setTimeout(function() {
        loader.classList.remove('visible');
      }, 150);
    }, 400);
  };
});
