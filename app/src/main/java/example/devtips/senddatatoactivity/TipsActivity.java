package example.devtips.senddatatoactivity;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_tips);


        setTitle(R.string.green_tips_title);
        TextView textMessage = (TextView) findViewById(R.id.text_message);

        textMessage.setText(
                new StringBuilder().append("If you want to help your planet, do not think that there is nothing that can be done. Everyone plays an important part in keeping the environment clean and safe. This means you can do so much and probably more than you think that you can. In fact, we are sharing with you 40 ways that you can go green. These tips are easy and things that anyone can use, so do not wait!\n").append("\n").append("1. Change your light bulbs. Changing just one of the light bulbs in your home to a florescent light could make a serious impact on your energy consumption. Imagine what can happen if you change them all.\n").append("\n").append("2. Turn your computers off at night. Even on energy saving mode you are using a lot of energy.\n").append("\n").append("3. Turn off lights in the home when they are not being used. This alone can cut energy costs greatly.\n").append("\n").append("4. Stop rinsing dishes before you put them in the dishwasher. This is a great source of water and energy waste!\n").append("\n").append("5. Take your own bags with you to the grocery store. You reduce the use of plastic, and  we all know how dangerous plastic is to the environment.\n").append("\n").append("6. Hang dry your clothes rather than use the clothes dryer.\n").append("\n").append("7. Reduce, Reuse and Recycle. Reduce the usage of packaged food items. Reuse scrap paper. Recycle old jars.\n").append("\n").append("8. Fix leaky faucets. This includes the toilet and the sinks in the home. Even a small leakage can cause good amount of wastage of water.\n").append("\n").append("9. Check if you can sign up for green power from your utility company. Also, conduct a quick energy audit of your home and replace electrical appliances with energy efficient appliances.\n").append("\n").append("10. When washing clothing, make sure that you use cold water or warm water. Using hot will take the machine longer to fill while consuming more water and using more energy.\n").append("\n").append("11. Instead of drinking bottled water, install a water cleaning system on your sink. Plastic bottles are usually not recycled.\n").append("\n").append("12. Cut the shower time in half and see your work into full swing!\n").append("\n").append("13. Take small shower instead of taking a bath.\n").append("\n").append("14. Plant a tree. Even one tree can make a serious impact and do great things for the environment.\n").append("\n").append("15. Recycling cell phones is another awesome way to go green. The average cell phone lasts about 18 months, but after this time they can be recycled and given life again!\n").append("\n").append("16. Use public transportation or carpooling to go to work. Better yet, walk when the weather is permitting.\n").append("\n").append("17. Pay all of your bills online. This will not only save you time but will also help to reduce paper wastage.\n").append("\n").append("18. Shop eco-friendly. Tons of environmentally friendly products are sold. Choose these items for benefits.\n").append("\n").append("19. Use recycled products whenever you can. This includes printer ink, paper, cardboard, etc. Even a reusable mug is good to have when you want to go green.\n").append("\n").append("20. Unplug all of your electronics when they are not being used.\n").append("\n").append("21. Buying products in bulk reduces the amount of packaging needed, thus providing considerable benefits to the environment.\n").append("\n").append("22. Do rainwater harvesting. Collect rainwater and use it to water your lawns and garden.\n").append("\n").append("23. Reduce the amount of junk mail that you receive.\n").append("\n").append("24. Go paperless. All bills have the option of being paid online, so take advantage of this option.\n").append("\n").append("25. Buy energy-efficient appliances in your home. There are many of them available and they can greatly benefit you!\n").append("\n").append("26. Buy organic food, make organic meals and try out new green recipes every other day.\n").append("\n").append("27. Buy vegetables that are grown locally. Not only will they taste better they also help you go green and reduce the use of paper, plastics and shipping material.\n").append("\n").append("28. Switch to cloth diapers instead of disposables. Even if you use one cloth diaper a day that will result in 365 fewer disposables in the landfill each year.\n").append("\n").append("29. Take your vehicle into the repair shop for regular maintenance. This will reduce emissions and more.\n").append("\n").append("30. If you have items around the home that you no longer need, do not throw them away. Instead give them to someone who would use them, donate to a thrift store or have a garage sale and put some cash in your pocket.\n").append("\n").append("31. Open windows in the home during daytime to let sunlight and fresh air come in.\n").append("\n").append("32. Set the thermostats at the proper temperatures.\n").append("\n").append("33. Pre-heating the oven is often times not necessary and simply eats up energy costs.\n").append("\n").append("34. Reuse scrap paper. Print on both sides of the paper to reduce paper wastage.\n").append("\n").append("35. Keep your tires inflated. Tires that are low of pressure are going to cause more wear on the car and more harm to the environment.\n").append("\n").append("36. Reduce your dependence on fossil fuels to reduce greenhouse gas emissions. The less we depend on them, the better.\n").append("\n").append("37. When in office, turn off all the peripherals such as printers, speakers and scanners when not in use.\n").append("\n").append("38. Try to put a cover on your pool when you are not using it. This will not only make the water cleaner but it will keep it from evaporating.\n").append("\n").append("39. Get e-tickets for the movies instead of buying paper tickets. Even file your taxes electronically to cut back on paper usage.\n").append("\n").append("40. Involve yourself in making your city clean and green. Encourage everyone in your neighborhood to join or start a recycling program and buy eco-friendly office products..\n").append("\n").append("As  you can see there is a ton of ways that you can go green. None of them are difficult, and anyone can use them to their advantage. If you care about the world and want to make a difference, know that you can, as proved with these 40 wonderful tips. You can make a difference in the world. Put these tips to good use and the many benefits that come your way will not disappoint you. It is so easy and so simple, why not go green?").toString());

    }
}
