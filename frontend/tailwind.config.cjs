/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    colors: {
      point: '#6F21D2',
      intacitve: '#B790E9',
      border: '#DCDCE7',
      hidden: '#F5F6F8',
      mainText: '#090909',
      mainTextWhite: '#D0D0D0',
      subText: '#424242',
      pointText: '#5F5ACE',
      warningText: '#ED2E51',
    },
    extend: {
      borderRadius: {
        sm: '3px',
        m: '10px',
        xl: '15px',
      },
    },
  },
  plugins: [require('tailwind-scrollbar-hide'), require('@tailwindcss/line-clamp')],
}
