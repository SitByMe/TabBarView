package zou.zohar.tabbarview.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zou.zohar.tabbarview.R;

public class TabFragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab, null);
        TextView tv = (TextView) inflate.findViewById(R.id.txt);
        tv.setBackgroundColor(Color.YELLOW);
        return inflate;
    }
}
