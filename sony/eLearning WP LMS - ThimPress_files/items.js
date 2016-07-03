var $items, $defaultItem = 'educationwp';



// Products List
// ---------------------------------------------------------
$items = {
  youniverse: {
    name      : 'Youniverse',
    tag       : 'Business',
    img       : 'https://image-tf.s3.envato.com/files/176716459/Preview.__large_preview.jpg',
    url       : 'http://youniverse.thimpress.com/',
    purchase  : 'http://themeforest.net/item/business-wordpress-theme-youniverse/15318703?ref=thimpress',
    tooltip   : 'Business WordPress Theme',
    responsive: true // To disable responsiveness buttons
  },
 charitywp: {
    name      : 'Charity WP',
    tag       : 'Charity',
    img       : 'https://image-tf.s3.envato.com/files/179845890/01_preview.__large_preview.jpg',
    url       : 'http://charitywp.thimpress.com/demo-4',
    purchase  : 'http://themeforest.net/item/charity-wordpress-theme-charity-wp/15593989?ref=thimpress',
    tooltip   : 'Charity WordPress Theme',
    responsive: true // To disable responsiveness buttons
  },
  educationwp: {
    name      : 'Education WP',
    tag       : 'Education',
    img       : '//preview-tf.s3.envato.com/files/162135744/01_preview.__large_preview.jpg',
    url       : 'http://educationwp.thimpress.com/landing/',
    purchase  : '//themeforest.net/item/education-wordpress-theme-education-wp/14058034?ref=thimpress',
    tooltip   : 'Education WordPress Theme',
    responsive: true // To disable responsiveness buttons
  },

  nem: {
    name      : 'NEM',
    tag       : 'Restaurant',
    img       : '//preview-tf.s3.envato.com/files/161769376/01_preview.__large_preview.jpg',
    url       : 'http://nem.thimpress.com/home-3',
    purchase  : '//themeforest.net/item/restaurant-wordpress-theme-nem/14025178?ref=thimpress',
    tooltip   : 'Restaurant WordPress Theme'
  },

  cakeart: {
    name      : 'Cake Art',
    tag       : 'Cake',
    img       : '//preview-tf.s3.envato.com/files/159420768/01_preview.__large_preview.jpg',
    url       : 'http://cakeart.thimpress.com/',
    purchase  : '//themeforest.net/item/bakery-cake-wordpress-theme-cake-art/13782850?ref=thimpress',
    tooltip   : 'Bakery & Cake WordPress Theme'
  },

  garage: {
    name      : 'Garage',
    tag       : 'Automotive',
    img       : '//preview-tf.s3.envato.com/files/162508231/01_preview.__large_preview.jpg',
    url       : 'http://garage.thimpress.com/',
    purchase  : '//themeforest.net/item/car-dealer-automotive-wordpress-theme-garage/13513609?ref=thimpress',
    tooltip   : 'Car Dealer Automotive WordPress Theme'
  },

  sailing: {
    name      : 'Sailing',
    tag       : 'Hotel',
    img       : '//preview-tf.s3.envato.com/files/154249458/01_preview.__large_preview.jpg',
    url       : 'http://sailing.thimpress.com/demo-2/',
    purchase  : '//themeforest.net/item/sailing-hotel-wordpress-theme-/13321455?ref=thimpress',
    tooltip   : 'Hotel WordPress Theme'
  },

  fastex: {
    name      : 'FastEx',
    tag       : 'Logistics',
    img       : '//preview-tf.s3.envato.com/files/145963850/01_preview.__large_preview.jpg',
    url       : 'http://fastex.thimpress.com/',
    purchase  : '//themeforest.net/item/transport-logistics-wordpress-theme-fastex/12532973?ref=thimpress',
    tooltip   : 'Logistics WordPress Theme'
  },

  resca: {
    name      : 'Resca',
    tag       : 'Restaurant',
    img       : '//preview-tf.s3.envato.com/files/165425174/01_preview.__large_preview.jpg',
    url       : '//resca.thimpress.com/',
    purchase  : '//themeforest.net/item/wordpress-restaurant-theme-resca/12124219?ref=thimpress',
    tooltip   : 'WordPress Restaurant Theme'
  },

  elearningwp: {
    name      : 'eLearning WP',
    tag       : 'LMS',
    img       : '//preview-tf.s3.envato.com/files/137786552/01_preview.__large_preview.jpg',
    url       : 'http://elearningwp.thimpress.com/',
    purchase  : '//themeforest.net/item/lms-wordpress-theme-elearning-wp/11797847?ref=thimpress',
    tooltip   : 'LMS WordPress Theme'
  },

  adot: {
    name      : 'adot',
    tag       : 'Shop',
    img       : '//preview-tf.s3.envato.com/files/139402308/01_preview.__large_preview.jpg',
    url       : '//adot.thimpress.com/home-09',
    purchase  : '//themeforest.net/item/ecommerce-wordpress-theme-adot/11733602?ref=thimpress',
    tooltip   : 'Ultimate WooCommerce WordPress Theme'
  },

  zoneshop: {
    name      : 'ZoneShop',
    tag       : 'Shop',
    img       : '//preview-tf.s3.envato.com/files/128185106/01_preview.__large_preview.jpg',
    url       : '//zoneshop.thimpress.com/',
    purchase  : '//themeforest.net/item/zoneshop-wonderful-responsive-woocommerce-theme/10960925?ref=thimpress',
    tooltip   : 'Swap, Book & Rent'
  },

  purify: {
    name      : 'Purify',
    tag       : 'Cleaning Service',
    img       : '//preview-tf.s3.envato.com/files/128405813/01_preview.__large_preview.jpg',
    url       : '//purify.thimpress.com/',
    purchase  : '//themeforest.net/item/purify-cleaning-service-wordpress-theme/10981419?ref=thimpress',
    tooltip   : 'Cleaning Service WordPress Theme'
  },

  exchangeshop: {
    name      : 'Exchange Shop',
    tag       : 'Shop',
    img       : '//preview-tf.s3.envato.com/files/124305242/01_preview.__large_preview.jpg',
    url       : '//exchangeshop.thimpress.com/',
    purchase  : '//themeforest.net/item/exchange-shop-responsive-wordpress-shop-theme/10163862?ref=thimpress',
    tooltip   : 'Exchange Shop WordPress Theme'
  },
  
  aloxo: {
    name      : 'Aloxo',
    tag       : 'Shop',
    img       : '//preview-tf.s3.envato.com/files/117827108/01_preview.__large_preview.png',
    url       : '//aloxo.thimpress.com/',
    purchase  : '//themeforest.net/item/aloxo-responsive-woocommerce-theme/9994416?ref=thimpress',
    tooltip   : 'Responsive WooCommerce WordPress Theme'
  },

  deskpress: {
    name      : 'DeskPress',
    tag       : 'HelpDesk',
    img       : '//preview-tf.s3.envato.com/files/109009562/01_preview.__large_preview.jpg',
    url       : '//deskpress.thimpress.com/',
    purchase  : '//themeforest.net/item/deskpress-effortless-helpdesk-support-wordpress-/9167183?ref=thimpress',
    tooltip   : 'Effortless Helpdesk WordPress Theme'
  },
  
  squareroot: {
    name      : 'Squareroot',
    tag       : 'Resume',
    img       : '//preview-tf.s3.envato.com/files/97544465/Preview/01_preview.__large_preview.jpg',
    url       : '//squareroot.thimpress.com/',
    purchase  : '//themeforest.net/item/wordpress-resume-cv-vcard-theme-squareroot/8254575?ref=thimpress',
    tooltip   : 'Resume WordPress Theme'
  },

  montana: {
    name      : 'Montana',
    tag       : 'Multipurpose',
    img       : '//preview-tf.s3.envato.com/files/96588362/Preview/01_preview.__large_preview.jpg',
    url       : '//montana.thimpress.com/',
    purchase  : '//themeforest.net/item/montana-responsive-multipurpose-wordpress-theme/7882488?ref=thimpress',
    tooltip   : 'Responsive Multipurpose WordPress Theme'
  }

};
