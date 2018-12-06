package com.youweather.sgg;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * La classe serve a definire e riempire lo stile che andrà a comporre lo stream dei post
 *
 */

public class ItemListBaseAdapter extends BaseAdapter {

	private static ArrayList<ItemDetails> itemDetailsrrayList;
	private LayoutInflater l_Inflater;
	private Integer[] imgid = {
			R.drawable.cloudy,
			R.drawable.rainy,
			R.drawable.snow,
			R.drawable.sunny,
			R.drawable.tstorm
    };
	
	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}


	//Imposta il layout del singolo post
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		//Nel caso in cui la View sia vuota si linkano gli oggetti
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item_details_view, null);
			holder = new ViewHolder();
			holder.txt_itemNameCity = (TextView) convertView.findViewById(R.id.namecity);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.description);
			holder.txt_itemNameUser = (TextView) convertView.findViewById(R.id.nameuser);
			holder.txt_temperature = (TextView) convertView.findViewById(R.id.temperature);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemNameCity.setText(itemDetailsrrayList.get(position).getCity());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getConditions());
		holder.txt_itemNameUser.setText(itemDetailsrrayList.get(position).getUser());
		holder.txt_temperature.setText(itemDetailsrrayList.get(position).getTemperature());
		holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemNameCity;
		TextView txt_itemDescription;
		TextView txt_itemNameUser;
		TextView txt_temperature;
		ImageView itemImage;
	}
	
	
	//Getters per gli elementi dello stream di cui fare l'overriding
	public int getCount() {
		return itemDetailsrrayList.size();
	}

	
	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	
	public long getItemId(int position) {
		return position;
	}

}
