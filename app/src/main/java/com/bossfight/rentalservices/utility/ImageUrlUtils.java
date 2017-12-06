package com.bossfight.rentalservices.utility;

import java.util.ArrayList;

public class ImageUrlUtils {
    static ArrayList<String> wishlistImageUri = new ArrayList<>();
    static ArrayList<String> wishListName = new ArrayList<>();
    static ArrayList<String> wishListDesc = new ArrayList<>();
    static ArrayList<String> wishListPrice = new ArrayList<>();
    static ArrayList<String> cartListName = new ArrayList<>();
    static ArrayList<String> cartListDesc = new ArrayList<>();
    static ArrayList<String> cartListPrice = new ArrayList<>();
    static ArrayList<String> cartListImageUri = new ArrayList<>();
    static ArrayList<String> orderListImageUri = new ArrayList<>();

    static ArrayList<String> productListName = new ArrayList<>();
    static ArrayList<String> productListDesc = new ArrayList<>();
    static ArrayList<String> productListPrice = new ArrayList<>();
    //Solar list
    static ArrayList<String> sproductListName = new ArrayList<>();
    static ArrayList<String> sproductListDesc = new ArrayList<>();
    static ArrayList<String> sproductListPrice = new ArrayList<>();
    //HVAC list
    static ArrayList<String> hproductListName = new ArrayList<>();
    static ArrayList<String> hproductListDesc = new ArrayList<>();
    static ArrayList<String> hproductListPrice = new ArrayList<>();
    //Smart list
    static ArrayList<String> tproductListName = new ArrayList<>();
    static ArrayList<String> tproductListDesc = new ArrayList<>();
    static ArrayList<String> tproductListPrice = new ArrayList<>();
    //Window List
    static ArrayList<String> wproductListName = new ArrayList<>();
    static ArrayList<String> wproductListDesc = new ArrayList<>();
    static ArrayList<String> wproductListPrice = new ArrayList<>();

    public static String[] getImageUrls() {
        String[] urls = new String[] {
                "https://static.pexels.com/photos/159397/solar-panel-array-power-sun-electricity-159397.jpeg",
                "https://static.pexels.com/photos/6245/kitchen-cooking-interior-decor-medium.jpg",
                "https://static.pexels.com/photos/6770/light-road-lights-night-medium.jpg",
                "https://static.pexels.com/photos/6041/nature-grain-moving-cereal-medium.jpg",
                "https://static.pexels.com/photos/7116/mountains-water-trees-lake-medium.jpg",
                "https://static.pexels.com/photos/6601/food-plate-yellow-white-medium.jpg",
                "https://static.pexels.com/photos/6695/summer-sun-yellow-spring-medium.jpg",
                "https://static.pexels.com/photos/7117/mountains-night-clouds-lake-medium.jpg",
                "https://static.pexels.com/photos/7262/clouds-ocean-seagull-medium.jpg",
                "https://static.pexels.com/photos/5968/wood-nature-dark-forest-medium.jpg",
                "https://static.pexels.com/photos/6101/hands-woman-art-hand-medium.jpg",
                "https://static.pexels.com/photos/6571/pexels-photo-medium.jpeg",
                "https://static.pexels.com/photos/6740/food-sugar-lighting-milk-medium.jpg",
                "https://static.pexels.com/photos/5659/sky-sunset-clouds-field-medium.jpg",
                "https://static.pexels.com/photos/6945/sunset-summer-golden-hour-paul-filitchkin-medium.jpg",
                "https://static.pexels.com/photos/6151/animal-cute-fur-white-medium.jpg",
                "https://static.pexels.com/photos/5696/coffee-cup-water-glass-medium.jpg",
                "https://static.pexels.com/photos/6789/flowers-petals-gift-flower-medium.jpg",
                "https://static.pexels.com/photos/7202/summer-trees-sunlight-trail-medium.jpg",
                "https://static.pexels.com/photos/7147/night-clouds-summer-trees-medium.jpg",
                "https://static.pexels.com/photos/6342/woman-notebook-working-girl-medium.jpg",
                "https://static.pexels.com/photos/5998/sky-love-people-romantic-medium.jpg",
                "https://static.pexels.com/photos/6872/cold-snow-nature-weather-medium.jpg",
                "https://static.pexels.com/photos/7045/pexels-photo-medium.jpeg",
                "https://static.pexels.com/photos/6923/mountains-fog-green-beauty-medium.jpg",
                "https://static.pexels.com/photos/6946/summer-bicycle-letsride-paul-filitchkin-medium.jpg",
                "https://static.pexels.com/photos/5650/sky-clouds-field-blue-medium.jpg",
                "https://static.pexels.com/photos/6292/blue-pattern-texture-macro-medium.jpg",
                "https://static.pexels.com/photos/6080/grass-lawn-technology-tablet-medium.jpg",
                "https://static.pexels.com/photos/7124/clouds-trees-medium.jpg",
                "https://static.pexels.com/photos/5923/woman-girl-teenager-wine-medium.jpg",
                "https://static.pexels.com/photos/6133/food-polish-cooking-making-medium.jpg",
                "https://static.pexels.com/photos/6224/hands-people-woman-working-medium.jpg",
                "https://static.pexels.com/photos/6414/rucola-young-argula-sproutus-medium.jpg",
                "https://static.pexels.com/photos/6739/art-graffiti-abstract-vintage-medium.jpg",
                "https://static.pexels.com/photos/6703/city-train-metal-public-transportation-medium.jpg",
                "https://static.pexels.com/photos/6851/man-love-woman-kiss-medium.jpg",
                "https://static.pexels.com/photos/6225/black-scissors-medium.jpg",
                "https://static.pexels.com/photos/7185/night-clouds-trees-stars-medium.jpg",
                "https://static.pexels.com/photos/5847/fashion-woman-girl-jacket-medium.jpg",
                "https://static.pexels.com/photos/5542/vintage-railroad-tracks-bw-medium.jpg",
                "https://static.pexels.com/photos/5938/food-salad-healthy-lunch-medium.jpg",
                "https://static.pexels.com/photos/7234/water-clouds-ocean-splash-medium.jpg",
                "https://static.pexels.com/photos/6418/flowers-flower-roses-rose-medium.jpg",
                "https://static.pexels.com/photos/6436/spring-flower-hyacinth-medium.jpg",
                "https://static.pexels.com/photos/6351/smartphone-desk-laptop-technology-medium.jpg",
                "https://static.pexels.com/photos/5618/fish-fried-mint-pepper-medium.jpg",
                "https://static.pexels.com/photos/6874/landscape-nature-water-rocks-medium.jpg",
                "https://static.pexels.com/photos/6918/bridge-fog-san-francisco-lets-get-lost-medium.jpg",
                "https://static.pexels.com/photos/5658/light-sunset-red-flowers-medium.jpg",
                "https://static.pexels.com/photos/6111/smartphone-friends-internet-connection-medium.jpg",
                "https://static.pexels.com/photos/5670/wood-fashion-black-stylish-medium.jpg",
                "https://static.pexels.com/photos/5838/hands-woman-hand-typing-medium.jpg",
                "https://static.pexels.com/photos/7050/sky-clouds-skyline-blue-medium.jpg",
                "https://static.pexels.com/photos/6036/nature-forest-tree-bark-medium.jpg",
                "https://static.pexels.com/photos/5676/art-camera-photography-picture-medium.jpg",
                "https://static.pexels.com/photos/6688/beach-sand-blue-ocean-medium.jpg",
                "https://static.pexels.com/photos/6901/sunset-clouds-golden-hour-lets-get-lost-medium.jpg",
                "https://static.pexels.com/photos/7260/rocks-fire-camping-medium.jpg",
                "https://static.pexels.com/photos/5672/dog-cute-adorable-play-medium.jpg",
                "https://static.pexels.com/photos/7261/rocks-trees-hiking-trail-medium.jpg",
                "https://static.pexels.com/photos/6411/smartphone-girl-typing-phone-medium.jpg",
                "https://static.pexels.com/photos/6412/table-white-home-interior-medium.jpg",
                "https://static.pexels.com/photos/6184/technology-keyboard-desktop-book-medium.jpg",
                "https://static.pexels.com/photos/7295/controller-xbox-gaming-medium.jpg",
                "https://static.pexels.com/photos/6732/city-cars-traffic-lights-medium.jpg",
                "https://static.pexels.com/photos/7160/bird-trees-medium.jpg",
                "https://static.pexels.com/photos/6999/red-hand-summer-berries-medium.jpg",
                "https://static.pexels.com/photos/5787/flowers-meadow-spring-green-medium.jpg",
                "https://static.pexels.com/photos/7136/water-rocks-stream-leaves-medium.jpg",
                "https://static.pexels.com/photos/7291/building-historical-church-religion-medium.jpg",
                "https://static.pexels.com/photos/6696/road-nature-summer-forest-medium.jpg",
                "https://static.pexels.com/photos/7294/garden-medium.jpg",
                "https://static.pexels.com/photos/6948/flight-sky-art-clouds-medium.jpg",
                "https://static.pexels.com/photos/7299/africa-animals-zoo-zebras-medium.jpg",
                "https://static.pexels.com/photos/6345/dark-brown-milk-candy-medium.jpg",
                "https://static.pexels.com/photos/7288/animal-dog-pet-park-medium.jpg",
                "https://static.pexels.com/photos/5863/nature-plant-leaf-fruits-medium.jpg",
                "https://static.pexels.com/photos/6625/pexels-photo-medium.jpeg",
                "https://static.pexels.com/photos/6708/stairs-people-sitting-architecture-medium.jpg",
                "https://static.pexels.com/photos/6429/smartphone-technology-music-white-medium.jpg",
                "https://static.pexels.com/photos/6574/pexels-photo-medium.jpeg",
                "https://static.pexels.com/photos/7287/grass-lawn-meadow-medium.jpg",
                "https://static.pexels.com/photos/6100/man-hands-holidays-looking-medium.jpg",
                "https://static.pexels.com/photos/6100/man-hands-holidays-looking-medium.jpg",
                "https://static.pexels.com/photos/6877/dog-pet-fur-brown-medium.jpg",
                "https://static.pexels.com/photos/6790/light-road-nature-iphone-medium.jpg",
                "https://static.pexels.com/photos/7077/man-people-office-writing-medium.jpg",
                "https://static.pexels.com/photos/6889/light-mountains-sunrise-california-medium.jpg",
                "https://static.pexels.com/photos/7274/leaf-fall-foliage-medium.jpg",
                "https://static.pexels.com/photos/7285/flowers-garden-medium.jpg",
                "https://static.pexels.com/photos/6821/light-sky-beach-sand-medium.jpg",
                "https://static.pexels.com/photos/7297/animal-africa-giraffe-medium.jpg",
                "https://static.pexels.com/photos/6154/sea-sky-water-clouds-medium.jpg",
                "https://static.pexels.com/photos/7059/man-people-space-desk-medium.jpg",
                "https://static.pexels.com/photos/6666/coffee-cup-mug-apple-medium.jpg",
                "https://static.pexels.com/photos/5949/food-nature-autumn-nuts-medium.jpg",
                "https://static.pexels.com/photos/7064/man-notes-macbook-computer-medium.jpg",
                "https://static.pexels.com/photos/5743/beach-sand-legs-shoes-medium.jpg",
                "https://static.pexels.com/photos/6355/desk-laptop-working-technology-medium.jpg",
                "https://static.pexels.com/photos/5844/sea-water-boats-boat-medium.jpg",
                "https://static.pexels.com/photos/5541/city-night-building-house-medium.jpg",
                "https://static.pexels.com/photos/7017/food-peppers-kitchen-yum-medium.jpg",
                "https://static.pexels.com/photos/5725/grey-luxury-carpet-silver-medium.jpg",
                "https://static.pexels.com/photos/6932/italian-vintage-old-beautiful-medium.jpg",
                "https://static.pexels.com/photos/7093/coffee-desk-notes-workspace-medium.jpg",
        };
        return urls;
    }

    public static String[] getSolarUrls() {
        String[] urls = new String[]{
                "https://static.pexels.com/photos/159397/solar-panel-array-power-sun-electricity-159397.jpeg",
                "https://static.pexels.com/photos/704810/pexels-photo-704810.jpeg",
                "https://stupiddope.com/wp-content/uploads/2015/05/Screen-Shot-2015-05-20-at-10.55.56-AM.png",
//                "https://images-na.ssl-images-amazon.com/images/I/71dSGIKOLtL._SL1500_.jpg",
//                "https://inhabitat.com/files/sun_bricks_main.jpg",
//                "https://static.techspot.com/images2/news/bigimage/2014/08/2014-08-08-image-3.jpg",
//                "https://media.treehugger.com/assets/images/2015/04/3506269_orig.jpg.650x0_q70_crop-smart.jpg",
        };
        return urls;
    }

    public static String[] getHvacUrls() {
        String[] urls = new String[]{
                "http://images.dunelm.com/i/dm/1000078711_main.jpg?$v8srpgrid$&img404=noimagedefault",
//                "https://www.canstarblue.com.au/wp-content/uploads/2016/10/Split-system-air-conditioner.jpg",
//                "http://images.nailsmag.com/post/M-exhaust-vent-nailsmag-1.jpg",
//                "https://static1.squarespace.com/static/52865350e4b045ae0cc9df4c/529e0cf0e4b066cb1f3f7a63/52b4f1ebe4b060add9439b18/1387590125426/Alpha_Mechanical_Ventilation_2e.jpg",
//
        };
        return urls;
    }

    public static String[] getSmartUrls() {
        String[] urls = new String[]{
                "https://cdn.shopify.com/s/files/1/1100/5760/products/81qzLmYUd-L._SL1500_large.jpg?v=1495436099",
//                "https://assets.pcmag.com/media/images/455753-ge-link-connected-bulb.jpg?thumb=y",
//                "https://www.allaboutyoursecurity.com/wp-content/uploads/2016/10/2gig-security-system.png",
//                "https://cdn.solarbotics.com/products/photos/f4195a9ac70dca62f2e43fa3400d826d/51755-IMG_7505.jpg",
//                "http://www.ikea.com/ms/en_US/media/visual_nav_images/seo_image/Smart%20lighting%20kits__Smart_Lighting_Vis_Nav_01.jpg",

        };
        return urls;
    }

    public static String[] getWindowUrls() {
        String[] urls = new String[]{
                "https://www.mediashower.com/img/349/bamboo-decorative-window-film-32-1.jpg",
//                "https://www.homedepot.com/hdus/en_US/DTCCOMNEW/fetch/DIY_Projects_and_Ideas/Decor/Guides/installing-window-film-HT-BG-DC-window-film-hero.jpg",
//                "https://cdn.shopify.com/s/files/1/1553/0873/products/tropical-oasis-privacy-window-film-10_large.jpg?v=1486587837",
//                "https://images-na.ssl-images-amazon.com/images/I/71WF09cDfGL._SY355_.jpg",
//                "https://images-na.ssl-images-amazon.com/images/I/61my%2Bd-J-EL._SY355_.jpg",
//                "https://www.windowfilm.co.uk/site/imagerotators/50/635/privacy-window-film-by-the-window-film-company-4.jpg?w=653&h=285&mode=pad&bgcolor=black",

        };
        return urls;
    }


    // Methods for Wishlist
    public void addWishlistImageUri(String wishlistImageUri) {
        this.wishlistImageUri.add(0,wishlistImageUri);
    }

    public void addWishListName(String wishnamelist) {
        this.wishListName.add(0,wishnamelist);
    }

    public void addWishListPrice(String wishpricelist) {
        this.wishListPrice.add(0,wishpricelist);
    }

    public void addWishListDesc(String wishdesclist) {
        this.wishListDesc.add(0,wishdesclist);
    }

    public void removeWishlistImageUri(int position) {
        this.wishlistImageUri.remove(position);
    }

    public ArrayList<String> getWishlistImageUri(){ return this.wishlistImageUri; }
    public ArrayList<String> getWishListName(){ return this.wishListName; }
    public ArrayList<String> getWishListDesc(){ return this.wishListDesc; }
    public ArrayList<String> getWishListPrice(){ return this.wishListPrice; }

    //---------------------------------------------------------------------------------------------------------------------
    // Methods for Products
    public void addProductListName(String productnamelist) { this.productListName.add(0,productnamelist); }
    public ArrayList<String> getProductListName(){ return this.productListName; }

    public void addProductListDesc(String productdesclist) { this.productListDesc.add(0,productdesclist); }
    public ArrayList<String> getProductListDesc(){ return this.productListDesc; }

    public void addProductListPrice(String productpricelist) { this.productListPrice.add(0,productpricelist); }
    public ArrayList<String> getProductListPrice(){ return this.productListPrice; }

    // Methods for Solar Products
    public void addsProductListName(String sproductnamelist) { this.sproductListName.add(0,sproductnamelist); }
    public ArrayList<String> getsProductListName(){ return this.sproductListName; }

    public void addsProductListDesc(String sproductdesclist) { this.sproductListDesc.add(0,sproductdesclist); }
    public ArrayList<String> getsProductListDesc(){ return this.sproductListDesc; }

    public void addsProductListPrice(String sproductpricelist) { this.sproductListPrice.add(0,sproductpricelist); }
    public ArrayList<String> getsProductListPrice(){ return this.sproductListPrice; }

    // Methods for HVAC Products
    public void addhProductListName(String hproductnamelist) { this.hproductListName.add(0,hproductnamelist); }
    public ArrayList<String> gethProductListName(){ return this.hproductListName; }

    public void addhProductListDesc(String hproductdesclist) { this.hproductListDesc.add(0,hproductdesclist); }
    public ArrayList<String> gethProductListDesc(){ return this.hproductListDesc; }

    public void addhProductListPrice(String hproductpricelist) { this.hproductListPrice.add(0,hproductpricelist); }
    public ArrayList<String> gethProductListPrice(){ return this.hproductListPrice; }

    // Methods for Smart Products
    public void addtProductListName(String tproductnamelist) { this.tproductListName.add(0,tproductnamelist); }
    public ArrayList<String> gettProductListName(){ return this.tproductListName; }

    public void addtProductListDesc(String tproductdesclist) { this.tproductListDesc.add(0,tproductdesclist); }
    public ArrayList<String> gettProductListDesc(){ return this.tproductListDesc; }

    public void addtProductListPrice(String tproductpricelist) { this.tproductListPrice.add(0,tproductpricelist); }
    public ArrayList<String> gettProductListPrice(){ return this.tproductListPrice; }

    // Methods for Window Products
    public void addwProductListName(String wproductnamelist) { this.wproductListName.add(0,wproductnamelist); }
    public ArrayList<String> getwProductListName(){ return this.wproductListName; }

    public void addwProductListDesc(String wproductdesclist) { this.wproductListDesc.add(0,wproductdesclist); }
    public ArrayList<String> getwProductListDesc(){ return this.wproductListDesc; }

    public void addwProductListPrice(String wproductpricelist) { this.wproductListPrice.add(0,wproductpricelist); }
    public ArrayList<String> getwProductListPrice(){ return this.wproductListPrice; }

    //---------------------------------------------------------------------------------------------------------------------

    // Methods for Cart
    public void addCartListImageUri(String wishlistImageUri) {
        this.cartListImageUri.add(0,wishlistImageUri);
    }

    public void addCartListName(String namelist) {
        this.cartListName.add(0,namelist);
    }

    public void addCartListPrice(String pricelist) {
        this.cartListPrice.add(0,pricelist);
    }

    public void addCartListDesc(String desclist) {
        this.cartListDesc.add(0,desclist);
    }

    public void removeCartListImageUri(int position) {
        this.cartListImageUri.remove(position);
    }

    public ArrayList<String> getCartListImageUri(){ return this.cartListImageUri; }
    public ArrayList<String> getCartListName(){ return this.cartListName; }
    public ArrayList<String> getCartListDesc(){ return this.cartListDesc; }
    public ArrayList<String> getCartListPrice(){ return this.cartListPrice; }

    //Methods for orders
    public void addOrderListImageUri(String wishlistImageUri) {
        this.orderListImageUri.add(0,wishlistImageUri);
    }

    public void removeOrderListImageUri(int position) {
        this.orderListImageUri.remove(position);
    }

    public ArrayList<String> getOrderListImageUri(){ return this.orderListImageUri; }
}
