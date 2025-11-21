package com.example.smartphoneproject.ui.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.smartphoneproject.R;
import com.example.smartphoneproject.databinding.FragmentEducationBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentEducationBinding binding;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentEducationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get all relevant UI items
        TextView tvSchool = root.findViewById(R.id.tvSchoolName);
        ImageView img = root.findViewById(R.id.ivSchool);
        TextView tvYears = root.findViewById(R.id.tvYears);
        TextView tvAges = root.findViewById(R.id.tvAges);
        TextView tvSubject = root.findViewById(R.id.tvSubject);
        Button btn = root.findViewById(R.id.btSchoolWeb);
        Button btPrevious = root.findViewById(R.id.btPrevious);
        Button btNext = root.findViewById(R.id.btNext);

        ViewPager viewPager = getActivity().findViewById(R.id.view_pager);

        //previous and next button listeners
        btPrevious.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();
            if (current > 0) {
                viewPager.setCurrentItem(current - 1, true);
            }
        });

        btNext.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();
            if (current < viewPager.getAdapter().getCount() - 1) {
                viewPager.setCurrentItem(current + 1, true);
            }
        });

        //respective tab contents
        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1){
            tvSchool.setText("Colegio El Castro");
            img.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.el_castro, getActivity().getTheme()));
            tvYears.setText("Years attended: 2007 - 2018");
            tvAges.setText("Ages I was when I attended: 3 - 12");
            tvSubject.setText("Favorite subject: Maths");
            btn.setOnClickListener(v -> {
                String url = "https://gecastrosanmiguel.com/el-castro/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
            btPrevious.setVisibility(View.INVISIBLE);
        }

        else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
            tvSchool.setText("Europäische Schule Karlsruhe");
            img.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.esk, getActivity().getTheme()));
            tvYears.setText("Years attended: 2018 - 2023");
            tvAges.setText("Ages I was when I attended: 12 - 18");
            tvSubject.setText("Favorite subject: ICT");
            btn.setOnClickListener(v -> {
                String url = "https://www.es-karlsruhe.eu/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }

        else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3){
            tvSchool.setText("UCLL Leuven-Limburg");
            img.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ucll, getActivity().getTheme()));
            tvYears.setText("Years attended: 2023 - Present");
            tvAges.setText("Ages I was when I attended: 18 - Present");
            tvSubject.setText("Favorite subject: 3D Graphics");
            btn.setOnClickListener(v -> {
                String url = "https://www.ucll.be";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }

        else if (getArguments().getInt(ARG_SECTION_NUMBER) == 4){
            tvSchool.setText("European University Cyprus");
            img.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.euc, getActivity().getTheme()));
            tvYears.setText("Years attended: 2025 - 2026");
            tvAges.setText("Ages I was when I attended: 20 - Present");
            tvSubject.setText("Favorite subject: Smartphone Programming");
            btn.setOnClickListener(v -> {
                String url = "https://euc.ac.cy";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
            btNext.setVisibility(View.INVISIBLE);
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}