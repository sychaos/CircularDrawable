package cloudist.cc.circularprogressview

import android.animation.*
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import cloudist.cc.library.CircularDrawable
import cloudist.cc.library.CircularProgressDrawable
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() { // Views
    var ivDrawable by Delegates.notNull<ImageView>()
    var ivDrawable2 by Delegates.notNull<ImageView>()
    var btStyle1 by Delegates.notNull<Button>()
    var btStyle2 by Delegates.notNull<Button>()
    var btStyle3 by Delegates.notNull<Button>()
    var btStyle4 by Delegates.notNull<Button>()
    var drawable by Delegates.notNull<Drawable>()

    internal var listener: View.OnClickListener = View.OnClickListener { v ->
        if (currentAnimation != null) {
            currentAnimation!!.cancel()
        }
        when (v.id) {
//            R.id.bt_style_1 -> currentAnimation = prepareStyle1Animation()
//            R.id.bt_style_2 -> currentAnimation = prepareStyle2Animation()
//            R.id.bt_style_3 -> currentAnimation = prepareStyle3Animation()
//            R.id.bt_style_4 -> currentAnimation = preparePulseAnimation()
//            R.id.iv_drawable -> currentAnimation = preparePressedAnimation()
        }
        currentAnimation!!.start()
    }

    internal var currentAnimation: Animator? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        ivDrawable = findViewById<View>(R.id.iv_drawable) as ImageView
        ivDrawable2 = findViewById<View>(R.id.iv_drawable2) as ImageView
        btStyle1 = findViewById<View>(R.id.bt_style_1) as Button
        btStyle2 = findViewById<View>(R.id.bt_style_2) as Button
        btStyle3 = findViewById<View>(R.id.bt_style_3) as Button
        btStyle4 = findViewById<View>(R.id.bt_style_4) as Button

//        //  使用builder 恩
//        drawable = CircularProgressDrawable.Builder()
//                .setRingWidth(resources.getDimensionPixelSize(R.dimen.drawable_ring_size))
//                .setOutlineColor(resources.getColor(android.R.color.darker_gray))
//                .setRingColor(resources.getColor(android.R.color.holo_green_light))
//                .setCenterColor(resources.getColor(android.R.color.holo_blue_dark))
//                .create()
        val drawable1 = CircularDrawable.Builder()
                .setOutlineColor(Color.BLUE)
                .setOutlineWidth(10)
                .create()

        val drawable2 = CircularDrawable.Builder()
                .setOutlineColor(Color.RED)
                .setOutlineWidth(6)
                .create()
        ivDrawable.setImageDrawable(drawable1)
        ivDrawable2.setImageDrawable(drawable2)
        hookUpListeners()
    }

    private fun hookUpListeners() {
        ivDrawable.setOnClickListener(listener)
        btStyle1.setOnClickListener(listener)
        btStyle2.setOnClickListener(listener)
        btStyle3.setOnClickListener(listener)
        btStyle4.setOnClickListener(listener)

    }

//    /**
//     * This animation was intended to keep a pressed state of the Drawable
//
//     * @return Animation
//     */
//    private fun preparePressedAnimation(): Animator {
//        //  属性动画 更改circleScale
//        val animation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
//                drawable.circleScale, 0.65f)
//        animation.duration = 120
//        return animation
//    }
//
//    /**
//     * This animation will make a pulse effect to the inner circle
//
//     * @return Animation
//     */
//    private fun preparePulseAnimation(): Animator {
//        val animation = AnimatorSet()
//
//        //  属性动画 更改circleScale
//        val firstBounce = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
//                drawable.circleScale, 0.88f)
//        firstBounce.duration = 300
//        //  插值器 CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
//        firstBounce.interpolator = CycleInterpolator(1f)
//        val secondBounce = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
//                0.75f, 0.83f)
//        secondBounce.duration = 300
//        secondBounce.interpolator = CycleInterpolator(1f)
//        val thirdBounce = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
//                0.75f, 0.80f)
//        thirdBounce.duration = 300
//        thirdBounce.interpolator = CycleInterpolator(1f)
//
//        // 顺序播放
//        animation.playSequentially(firstBounce, secondBounce, thirdBounce)
//        return animation
//    }
//
//    /**
//     * Style 1 animation will simulate a indeterminate loading while taking advantage of the inner
//     * circle to provide a progress sense
//
//     * @return Animation
//     */
//    private fun prepareStyle1Animation(): Animator {
//        val animation = AnimatorSet()
//
//        //  属性动画 更改circleScale，progress
//        val indeterminateAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0f, 3600f)
//        indeterminateAnimation.duration = 3600
//
//        val innerCircleAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0f, 0.5f)
//        innerCircleAnimation.duration = 3600
//        innerCircleAnimation.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationStart(animation: Animator) {
//                drawable.isIndeterminate = true
//            }
//
//            override fun onAnimationEnd(animation: Animator) {
//                indeterminateAnimation.end()
//                drawable.isIndeterminate = false
//                drawable.progress = 0f
//            }
//        })
//
//        // 同时播放
//        animation.playTogether(innerCircleAnimation, indeterminateAnimation)
//        return animation
//    }
//
//    /**
//     * Style 2 animation will fill the outer ring while applying a color effect from red to green
//
//     * @return Animation
//     */
//    private fun prepareStyle2Animation(): Animator {
//        val animation = AnimatorSet()
//
//        //  属性动画 更改ringColor，progress
//        val progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY,
//                0f, 1f)
//        progressAnimation.duration = 3600
//        progressAnimation.interpolator = AccelerateDecelerateInterpolator()
//
//        val colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
//                resources.getColor(android.R.color.holo_red_dark),
//                resources.getColor(android.R.color.holo_green_light))
//        colorAnimator.setEvaluator(ArgbEvaluator())
//        colorAnimator.duration = 3600
//
//        animation.playTogether(progressAnimation, colorAnimator)
//        return animation
//    }
//
//    /**
//     * Style 3 animation will turn a 3/4 animation with Anticipate/Overshoot interpolation to a
//     * blank waiting - like state, wait for 2 seconds then return to the original state
//
//     * @return Animation
//     */
//    private fun prepareStyle3Animation(): Animator {
//        val animation = AnimatorSet()
//
//        //  属性动画 更改progress，circleScale 先从0到3/4，后从3/4到0
//        val progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0.75f, 0f)
//        progressAnimation.duration = 1200
//        progressAnimation.interpolator = AnticipateInterpolator()
//
//        val innerCircleAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0.75f, 0f)
//        innerCircleAnimation.duration = 1200
//        innerCircleAnimation.interpolator = AnticipateInterpolator()
//
//        val invertedProgress = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0f, 0.75f)
//        invertedProgress.duration = 1200
//        invertedProgress.startDelay = 3200
//        invertedProgress.interpolator = OvershootInterpolator()
//
//        val invertedCircle = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0f, 0.75f)
//        invertedCircle.duration = 1200
//        invertedCircle.startDelay = 3200
//        invertedCircle.interpolator = OvershootInterpolator()
//
//        animation.playTogether(progressAnimation, innerCircleAnimation, invertedProgress, invertedCircle)
//        return animation
//    }
}
