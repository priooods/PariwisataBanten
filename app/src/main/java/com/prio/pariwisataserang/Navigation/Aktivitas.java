package com.prio.pariwisataserang.Navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prio.pariwisataserang.Custom.DisableViewpager;
import com.prio.pariwisataserang.R;

import java.util.ArrayList;
import java.util.List;

public class Aktivitas extends Fragment {

    TabLayout tabLayout ;
    DisableViewpager viewpager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aktivitas,container,false);

        tabLayout = view.findViewById(R.id.tablayout_aktivitas);
        viewpager = view.findViewById(R.id.viewpager_aktivitas);
        fragmentpager pager = new fragmentpager(getActivity().getSupportFragmentManager());
        pager.AddFragment(new Favorite(),"Favorite");
        pager.AddFragment(new Menyukai(),"Menyukai");
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(pager);

        return view;
    }


    public class fragmentpager extends FragmentStatePagerAdapter {
        private List<String> stringList = new ArrayList<>();
        private List<Fragment> fragmentList = new ArrayList<>();

        public fragmentpager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }

        @Override
        public int getCount() {
            return stringList.size();
        }

        public void AddFragment (Fragment fragment, String string){
            fragmentList.add(fragment);
            stringList.add(string);
        }
    }
}
