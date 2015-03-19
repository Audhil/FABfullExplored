package com.wordpress.smdaudhilbe.mohammed_2284.fabfullyexplored;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {

    //  for activity_main
//    private ImageButton fab;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initViews();
//    }
//
//    private void initViews() {
//        fab = (ImageButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fab.setSelected(!fab.isSelected());
//                fab.setImageResource(fab.isSelected() ? R.drawable.animated_plus : R.drawable.animated_minus);
//
//                Drawable drawable = fab.getDrawable();
//                if (drawable instanceof Animatable) {
//                    ((Animatable) drawable).start();
//                }
//            }
//        });
//    }

    //  for with_mini_action_buttons
    private ImageButton fab;

    private boolean expanded = false;

    private View fabAction1;
    private View fabAction2;
    private View fabAction3;

    private float offset1;
    private float offset2;
    private float offset3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.with_mini_action_buttons);
        final ViewGroup fabContainer = (ViewGroup) findViewById(R.id.fab_container);
        fab = (ImageButton) findViewById(R.id.fab);
        fabAction1 = findViewById(R.id.fab_action_1);
        fabAction2 = findViewById(R.id.fab_action_2);
        fabAction3 = findViewById(R.id.fab_action_3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                if (expanded) {
                    expandFab();
                } else {
                    collapseFab();
                }
            }
        });

        fabContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fabContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                offset1 = fab.getY() - fabAction1.getY();
                fabAction1.setTranslationY(offset1);
                offset2 = fab.getY() - fabAction2.getY();
                fabAction2.setTranslationY(offset2);
                offset3 = fab.getY() - fabAction3.getY();
                fabAction3.setTranslationY(offset3);
                return true;
            }
        });
    }

    private void collapseFab() {
        fab.setImageResource(R.drawable.animated_minus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createCollapseAnimator(fabAction1, offset1), createCollapseAnimator(fabAction2, offset2), createCollapseAnimator(fabAction3, offset3));
        animatorSet.start();
        animateFab();
    }

    private void expandFab() {
        fab.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(fabAction1, offset1), createExpandAnimator(fabAction2, offset2), createExpandAnimator(fabAction3, offset3));
        animatorSet.start();
        animateFab();
    }

    private static final String TRANSLATION_Y = "translationY";

    private Animator createCollapseAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset).setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private Animator createExpandAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0).setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private void animateFab() {
        Drawable drawable = fab.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}