module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint'
  },
  "env": {
    "browser": true,
    "es6": true,
    "mocha": true
  },
  "extends": [
    "eslint:recommended",
    "plugin:vue/recommended",
  ],
  plugins: [
    'vue'
  ],
  rules: {
    indent: [
      'error', 4
    ],
    'linebreak-style': [
      'error', 'unix'
    ],
    eqeqeq: 1,
    quotes: ['error', 'single'],
    semi: ['error', 'always'],
    'generator-star-spacing': 'off',
    'no-debugger': "process.env.NODE_ENV" === 'production' ? 'error' : 'off'
  },
};
