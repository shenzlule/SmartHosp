package org.hospital.hospitalbookup.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.models.Doctor;

import java.util.List;

public class DoctorAdapter extends BaseAdapter {

    private Context context;
    private List<Doctor> doctors;

    public DoctorAdapter(Context context, List<Doctor> doctors) {
        this.context = context;
        this.doctors = doctors;
    }

    @Override
    public int getCount() {
        return doctors.size();
    }

    @Override
    public Object getItem(int i) {
        return doctors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false);

        ImageView imageView = convertView.findViewById(R.id.doctorImage);
        TextView name = convertView.findViewById(R.id.doctorName);
        TextView specialty = convertView.findViewById(R.id.doctorSpecialty);

        Doctor doc = doctors.get(position);

        name.setText(doc.getName());
        specialty.setText(doc.getSpecialty());

        Glide.with(context)
                .load(doc.getImageResId())
                .circleCrop()
                .into(imageView);

        return convertView;
    }
}
