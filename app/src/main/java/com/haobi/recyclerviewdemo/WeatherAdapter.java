package com.haobi.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 15739 on 2019/8/2.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Weather> mWeatherList;

    //静态内部类：可以不依赖于外部类实例化而被实例化
    static class ViewHolder extends RecyclerView.ViewHolder{

        //添加weatherView变量来保存子项最外层布局（图片+文字区域）的实例
        View weatherView;

        ImageView weatherImage;
        TextView weatherName;
        //静态内部类的构造函数
        public ViewHolder(View view){
            //传入的view就是RecyclerView子项的最外层布局
            super(view);
            //根据布局获取布局实例
            weatherView = view;//图片加文字区域
            weatherImage = (ImageView) view.findViewById(R.id.weather_image);
            weatherName = (TextView) view.findViewById(R.id.weather_name);
        }
    }

    //构造函数
    public WeatherAdapter(List<Weather> weatherList){
        //把要展示的数据源传递进来，并赋给一个全局变量mWeatherList
        mWeatherList = weatherList;
    }

    //创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载weather_item布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        //创建ViewHolder实例，并将布局传入ViewHolder构造函数
        final ViewHolder holder = new ViewHolder(view);
        holder.weatherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取position
                int position = holder.getAdapterPosition();
                //通过position获取实例
                Weather weather = mWeatherList.get(position);
                Toast.makeText(v.getContext(), "View:"+weather.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.weatherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取position
                int position = holder.getAdapterPosition();
                //通过position获取实例
                Weather weather = mWeatherList.get(position);
                Toast.makeText(v.getContext(), "Image:"+weather.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //将ViewHolder实例返回
        return holder;
    }

    //对RecyclerView子项的数据进行赋值，会在每个子项滚动到屏幕内时执行
    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
        //通过position参数得到当前Weather实例
        Weather weather = mWeatherList.get(position);
        //将数据设置到ViewHolder的ImageView和TextView中
        holder.weatherImage.setImageResource(weather.getImageId());
        holder.weatherName.setText(weather.getName());
    }

    //返回数据源长度
    @Override
    public int getItemCount() {
        //返回RecyclerView一共有多少个子项
        return mWeatherList.size();
    }
}
