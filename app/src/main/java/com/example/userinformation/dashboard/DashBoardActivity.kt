package com.example.userinformation.dashboard

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.userinformation.Groceries.GroceriesActivity
import com.example.userinformation.R
import com.example.userinformation.addtocart.AddToCartFragment
import com.example.userinformation.cloth.clothproducts.ClothListFragment
import com.example.userinformation.cloth.clothproducts.model.ClothItem
import com.example.userinformation.customViewForRecycleView.CARVActivity
import com.example.userinformation.dashboard.productdetails.ViewProductsActivity
import com.example.userinformation.databinding.ActivityMainBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.fragmentToActivity.FragmentToActivity
import com.example.userinformation.home.Home
import com.example.userinformation.informationform.InformationFormActivity
import com.example.userinformation.informationform.emergency_contact_form.EmergencyContactFormActivity
import com.example.userinformation.intent.IntentActivity
import com.example.userinformation.pharmacy.Pharmacy
import com.example.userinformation.userdetails.LoginActivity
import com.example.userinformation.wishlist.WishListFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var adapter: ImageViewAdapter
    private lateinit var doLayout: LinearLayout
    private lateinit var dbHelper: ProductDBHelper

    private val flg = "data_flg"
    private val DATA_FETCHED = "data_fetched"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bottomNav: BottomNavigationView

    companion object {
        val REQUEST_CODE_SPEECH_INPUT = 1
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = ProductDBHelper(this)

        imageContainer()


        val gradientColors = listOf(
            ContextCompat.getColor(this, R.color.pink),
            ContextCompat.getColor(this, R.color.white),
            ContextCompat.getColor(this, R.color.seek_bar_background)
        )
        val gradientAngles = listOf(
            GradientDrawable.Orientation.BL_TR,
            GradientDrawable.Orientation.TOP_BOTTOM,
            GradientDrawable.Orientation.BR_TL
        )
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val gradientAngle = gradientAngles[position % gradientAngles.size]

                val gradientDrawable = GradientDrawable(gradientAngle, gradientColors.toIntArray())
                gradientDrawable.cornerRadius = resources.getDimension(R.dimen.card_corner_radius)

                viewPager2.background = gradientDrawable

                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 4000)
                adapter.updateDotIndicator(position)
            }
        })


        topAppBar = binding.appBar

        topAppBarItem()
//        val mic = findViewById<ActionMenuItemView>(R.id.microphone)

//        val logOut = findViewById<ActionMenuItemView>(R.id.logout)


//        logOut.setOnClickListener{
//            logOutUser()
//        }
//
//        mic.setOnClickListener {
//            speechRecognizer()
//
//        }


        sharedPreferences = this.getSharedPreferences(flg, Context.MODE_PRIVATE)
        val dataFetched = sharedPreferences.getBoolean(DATA_FETCHED, false)

        if (!dataFetched) {
            clothRetrofit()
            val editor = sharedPreferences.edit()
            editor.putBoolean(DATA_FETCHED, true)
            editor.apply()
        }
        bottomNav = binding.bottomNavigationItem.bottomNavigationView
        bottomNav.selectedItemId = R.id.navigation_home

        bottomNavigationItemSelected()

    }

    private fun topAppBarItem() {
        topAppBar.setOnMenuItemClickListener{

            when(it.itemId){
                R.id.nav_fav -> {
                    replaceFragment(WishListFragment())
                    true
                }
                R.id.nav_cart -> {
                    replaceFragment(AddToCartFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun showd(): Boolean {
        startActivity(Intent(this, IntentActivity::class.java))
        return true
    }

    private fun logOutUser() {
        Toast.makeText(this, "Search Click !", Toast.LENGTH_SHORT).show()
        val editor = getSharedPreferences("LoginInfo", MODE_PRIVATE).edit()
        editor.putBoolean("flag", false)
        editor.apply()

        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun speechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN")
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "mr-IN")
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US, hi-IN, mr-IN")
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")
        intent.putExtra(
            RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,
            1000
        )
        startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val speechResult = result?.get(0)
                if (!speechResult.isNullOrEmpty()) {
                    showMessage(speechResult)
                }
            }
        }
    }

    private fun showMessage(message: String) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(message)
            .setTitle("Speech Recognition Result")
            .setPositiveButton("OKAY") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()

    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }


    private val runnable = Runnable {
        viewPager2.currentItem += 1
    }

    private fun imageContainer() {
        viewPager2 = binding.viewPager

        handler = Handler(Looper.myLooper()!!)
        doLayout = binding.dotLayout

        val listImage = listOf(
            R.drawable.money,
            R.drawable.specialoffer,
            R.drawable.cloth
        )

        val title = listOf(
            "ACTIVATE FINANCE AMOUNT",
            "TODAY SPECIAL OFFER",
            "CLOTH STORE"
        )
        val des = listOf(
            "Minimum INR5000 financing amount is required",
            "Festive season approaching means irresistible discounts and perfect gifts.",
            "Stylish clothing for both men and women."
        )
        adapter = ImageViewAdapter(listImage, title, des, viewPager2, doLayout)
        viewPager2.adapter = adapter

        for (i in listImage.indices) {
            val dot = ImageView(this)
            dot.setImageResource(if (i == 0) R.drawable.selected_dot else R.drawable.unselected_dot)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            doLayout.addView(dot, params)
        }
        viewPager2.offscreenPageLimit = 1
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    fun onCloth(view: View) {
        if (view.id == R.id.btnCloths) {
            replaceFragment(ClothListFragment())
        }
    }

    fun onForm(view: View){
        if (view.id==R.id.btnInfoForm){
            startActivity(Intent(this, InformationFormActivity::class.java))
        }
    }
    fun onHome(view: View) {
        if (view.id == R.id.btn_home) {
            startActivity(Intent(this, Home::class.java))

        }
    }

    fun onGuardian(view: View) {
        if (view.id == R.id.btn_guardian) {
            startActivity(Intent(this, EmergencyContactFormActivity::class.java))
        }
    }

    fun onPharmacy(view: View) {
        if (view.id == R.id.btn_pharmacy) {
            startActivity(Intent(this, Pharmacy::class.java))
        }
    }

    fun onGroceries(view: View) {
        if (view.id == R.id.btn_groceries) {
            startActivity(Intent(this, GroceriesActivity::class.java))
        }
    }
    fun onNotes(view: View) {
        if (view.id == R.id.customAdaptor) {
            startActivity(Intent(this, ViewProductsActivity::class.java))
        }
    }

    fun onProduct(view: View) {
        if (view.id == R.id.products) {
            startActivity(Intent(this, IntentActivity::class.java))
        }
    }

    fun onCARV(view: View) {
        if (view.id == R.id.carv) {
            startActivity(Intent(this, CARVActivity::class.java))
        }
    }

    fun onFragmentToActivity(view: View) {
        if (view.id == R.id.fragment) {
            startActivity(Intent(this, FragmentToActivity::class.java))
        }
    }

    private fun clothRetrofit() {

        dbHelper = ProductDBHelper(this)
        val listType = object : TypeToken<List<ClothItem>>() {}.type
        val clothItems: List<ClothItem> = Gson().fromJson(
            "[   {     \"id\": 1,     \"title\": \"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops\",     \"price\": 109.95,     \"description\": \"Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday\",     \"category\": \"men's clothing\",     \"image\": \"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg\",     \"rating\": {       \"rate\": 3.9,       \"count\": 120     }   },   {     \"id\": 2,     \"title\": \"Mens Casual Premium Slim Fit T-Shirts \",     \"price\": 22.3,     \"description\": \"Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.\",     \"category\": \"men's clothing\",     \"image\": \"https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg\",     \"rating\": {       \"rate\": 4.1,       \"count\": 259     }   },   {     \"id\": 3,     \"title\": \"Mens Cotton Jacket\",     \"price\": 55.99,     \"description\": \"great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.\",     \"category\": \"men's clothing\",     \"image\": \"https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg\",     \"rating\": {       \"rate\": 4.7,       \"count\": 500     }   },   {     \"id\": 4,     \"title\": \"Mens Casual Slim Fit\",     \"price\": 15.99,     \"description\": \"The color could be slightly different between on the screen and in practice. / Please note that body builds vary by person, therefore, detailed size information should be reviewed below on the product description.\",     \"category\": \"men's clothing\",     \"image\": \"https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg\",     \"rating\": {       \"rate\": 2.1,       \"count\": 430     }   },   {     \"id\": 5,     \"title\": \"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet\",     \"price\": 695,     \"description\": \"From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.\",     \"category\": \"jewelery\",     \"image\": \"https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg\",     \"rating\": {       \"rate\": 4.6,       \"count\": 400     }   },   {     \"id\": 6,     \"title\": \"Solid Gold Petite Micropave \",     \"price\": 168,     \"description\": \"Satisfaction Guaranteed. Return or exchange any order within 30 days.Designed and sold by Hafeez Center in the United States. Satisfaction Guaranteed. Return or exchange any order within 30 days.\",     \"category\": \"jewelery\",     \"image\": \"https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg\",     \"rating\": {       \"rate\": 3.9,       \"count\": 70     }   },   {     \"id\": 7,     \"title\": \"White Gold Plated Princess\",     \"price\": 9.99,     \"description\": \"Classic Created Wedding Engagement Solitaire Diamond Promise Ring for Her. Gifts to spoil your love more for Engagement, Wedding, Anniversary, Valentine's Day...\",     \"category\": \"jewelery\",     \"image\": \"https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg\",     \"rating\": {       \"rate\": 3,       \"count\": 400     }   },   {     \"id\": 8,     \"title\": \"Pierced Owl Rose Gold Plated Stainless Steel Double\",     \"price\": 10.99,     \"description\": \"Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel\",     \"category\": \"jewelery\",     \"image\": \"https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg\",     \"rating\": {       \"rate\": 1.9,       \"count\": 100     }   },   {     \"id\": 9,     \"title\": \"WD 2TB Elements Portable External Hard Drive - USB 3.0 \",     \"price\": 64,     \"description\": \"USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on user’s hardware configuration and operating system\",     \"category\": \"electronics\",     \"image\": \"https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg\",     \"rating\": {       \"rate\": 3.3,       \"count\": 203     }   },   {     \"id\": 10,     \"title\": \"SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s\",     \"price\": 109,     \"description\": \"Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.)\",     \"category\": \"electronics\",     \"image\": \"https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg\",     \"rating\": {       \"rate\": 2.9,       \"count\": 470     }   },   {     \"id\": 11,     \"title\": \"Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5\",     \"price\": 109,     \"description\": \"3D NAND flash are applied to deliver high transfer speeds Remarkable transfer speeds that enable faster bootup and improved overall system performance. The advanced SLC Cache Technology allows performance boost and longer lifespan 7mm slim design suitable for Ultrabooks and Ultra-slim notebooks. Supports TRIM command, Garbage Collection technology, RAID, and ECC (Error Checking & Correction) to provide the optimized performance and enhanced reliability.\",     \"category\": \"electronics\",     \"image\": \"https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg\",     \"rating\": {       \"rate\": 4.8,       \"count\": 319     }   },   {     \"id\": 12,     \"title\": \"WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive\",     \"price\": 114,     \"description\": \"Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty\",     \"category\": \"electronics\",     \"image\": \"https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg\",     \"rating\": {       \"rate\": 4.8,       \"count\": 400     }   },   {     \"id\": 13,     \"title\": \"Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin\",     \"price\": 599,     \"description\": \"21. 5 inches Full HD (1920 x 1080) widescreen IPS display And Radeon free Sync technology. No compatibility for VESA Mount Refresh Rate: 75Hz - Using HDMI port Zero-frame design | ultra-thin | 4ms response time | IPS panel Aspect ratio - 16: 9. Color Supported - 16. 7 million colors. Brightness - 250 nit Tilt angle -5 degree to 15 degree. Horizontal viewing angle-178 degree. Vertical viewing angle-178 degree 75 hertz\",     \"category\": \"electronics\",     \"image\": \"https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg\",     \"rating\": {       \"rate\": 2.9,       \"count\": 250     }   },   {     \"id\": 14,     \"title\": \"Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultrawide Screen QLED \",     \"price\": 999.99,     \"description\": \"49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR with dual 27 inch screen side by side QUANTUM DOT (QLED) TECHNOLOGY, HDR support and factory calibration provides stunningly realistic and accurate color and contrast 144HZ HIGH REFRESH RATE and 1ms ultra fast response time work to eliminate motion blur, ghosting, and reduce input lag\",     \"category\": \"electronics\",     \"image\": \"https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg\",     \"rating\": {       \"rate\": 2.2,       \"count\": 140     }   },   {     \"id\": 15,     \"title\": \"BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats\",     \"price\": 56.99,     \"description\": \"Note:The Jackets is US standard size, Please choose size as your usual wear Material: 100% Polyester; Detachable Liner Fabric: Warm Fleece. Detachable Functional Liner: Skin Friendly, Lightweigt and Warm.Stand Collar Liner jacket, keep you warm in cold weather. Zippered Pockets: 2 Zippered Hand Pockets, 2 Zippered Pockets on Chest (enough to keep cards or keys)and 1 Hidden Pocket Inside.Zippered Hand Pockets and Hidden Pocket keep your things secure. Humanized Design: Adjustable and Detachable Hood and Adjustable cuff to prevent the wind and water,for a comfortable fit. 3 in 1 Detachable Design provide more convenience, you can separate the coat and inner as needed, or wear it together. It is suitable for different season and help you adapt to different climates\",     \"category\": \"women's clothing\",     \"image\": \"https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg\",     \"rating\": {       \"rate\": 2.6,       \"count\": 235     }   },   {     \"id\": 16,     \"title\": \"Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket\",     \"price\": 29.95,     \"description\": \"100% POLYURETHANE(shell) 100% POLYESTER(lining) 75% POLYESTER 25% COTTON (SWEATER), Faux leather material for style and comfort / 2 pockets of front, 2-For-One Hooded denim style faux leather jacket, Button detail on waist / Detail stitching at sides, HAND WASH ONLY / DO NOT BLEACH / LINE DRY / DO NOT IRON\",     \"category\": \"women's clothing\",     \"image\": \"https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg\",     \"rating\": {       \"rate\": 2.9,       \"count\": 340     }   },   {     \"id\": 17,     \"title\": \"Rain Jacket Women Windbreaker Striped Climbing Raincoats\",     \"price\": 39.99,     \"description\": \"Lightweight perfet for trip or casual wear---Long sleeve with hooded, adjustable drawstring waist design. Button and zipper front closure raincoat, fully stripes Lined and The Raincoat has 2 side pockets are a good size to hold all kinds of things, it covers the hips, and the hood is generous but doesn't overdo it.Attached Cotton Lined Hood with Adjustable Drawstrings give it a real styled look.\",     \"category\": \"women's clothing\",     \"image\": \"https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2.jpg\",     \"rating\": {       \"rate\": 3.8,       \"count\": 679     }   },   {     \"id\": 18,     \"title\": \"MBJ Women's Solid Short Sleeve Boat Neck V \",     \"price\": 9.85,     \"description\": \"95% RAYON 5% SPANDEX, Made in USA or Imported, Do Not Bleach, Lightweight fabric with great stretch for comfort, Ribbed on sleeves and neckline / Double stitching on bottom hem\",     \"category\": \"women's clothing\",     \"image\": \"https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg\",     \"rating\": {       \"rate\": 4.7,       \"count\": 130     }   },   {     \"id\": 19,     \"title\": \"Opna Women's Short Sleeve Moisture\",     \"price\": 7.95,     \"description\": \"100% Polyester, Machine wash, 100% cationic polyester interlock, Machine Wash & Pre Shrunk for a Great Fit, Lightweight, roomy and highly breathable with moisture wicking fabric which helps to keep moisture away, Soft Lightweight Fabric with comfortable V-neck collar and a slimmer fit, delivers a sleek, more feminine silhouette and Added Comfort\",     \"category\": \"women's clothing\",     \"image\": \"https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_.jpg\",     \"rating\": {       \"rate\": 4.5,       \"count\": 146     }   },   {     \"id\": 20,     \"title\": \"DANVOUY Womens T Shirt Casual Cotton Short\",     \"price\": 12.99,     \"description\": \"95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.\",     \"category\": \"women's clothing\",     \"image\": \"https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg\",     \"rating\": {       \"rate\": 3.6,       \"count\": 145     }   } ]",
            listType
        )
        clothItems.forEach { item ->
            dbHelper.insertClothItem(item)

        }
    }

    private fun bottomNavigationItemSelected() {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_list -> {

                    replaceFragment(ClothListFragment())

                    true
                }

                R.id.navigation_fav -> {

                    replaceFragment(WishListFragment())
                    true
                }

                R.id.navigation_cart -> {

                    replaceFragment(AddToCartFragment())

                    true
                }

                else -> false
            }
        }
    }
    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.cloth_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()

        } else {
            super.onBackPressed()
            finish()
        }
    }
}