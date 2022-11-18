package com.gyphytestapp.presentation.main

import androidx.lifecycle.ViewModel
import com.gyphytestapp.model.PreviewWebp
import com.gyphytestapp.model_o.Data
import com.gyphytestapp.model_o.Images
import com.gyphytestapp.model_o.Original
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    fun provideList() : List<Data> = listOf(
            Data(
                "1",
                Images(
                    Original("https://media2.giphy.com/media/RKsR28Jzn7sqI/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media2.giphy.com/media/RKsR28Jzn7sqI/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),
            Data(
                "2", Images(
                    Original("https://media2.giphy.com/media/l3V0ADaHw4aAbfwOI/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media2.giphy.com/media/l3V0ADaHw4aAbfwOI/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),

            Data(
                "3", Images(
                    Original("https://media4.giphy.com/media/uQvxobRExS9nG/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media4.giphy.com/media/uQvxobRExS9nG/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),

            Data(
                "4", Images(
                    Original("https://media2.giphy.com/media/1Fjxzv70Zvyqk/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media2.giphy.com/media/1Fjxzv70Zvyqk/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),
            Data(
                "5",
                Images(
                    Original("https://media2.giphy.com/media/RKsR28Jzn7sqI/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media2.giphy.com/media/RKsR28Jzn7sqI/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),
            Data(
                "6", Images(
                    Original("https://media2.giphy.com/media/l3V0ADaHw4aAbfwOI/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media2.giphy.com/media/l3V0ADaHw4aAbfwOI/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),

            Data(
                "7", Images(
                    Original("https://media4.giphy.com/media/uQvxobRExS9nG/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media4.giphy.com/media/uQvxobRExS9nG/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),
            Data(
                "8", Images(
                    Original("https://media2.giphy.com/media/1Fjxzv70Zvyqk/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media2.giphy.com/media/1Fjxzv70Zvyqk/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),
            Data(
                "9", Images(
                    Original("https://media2.giphy.com/media/1Fjxzv70Zvyqk/giphy.gif?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy.gif&ct=g"),
                    PreviewWebp("https://media2.giphy.com/media/1Fjxzv70Zvyqk/giphy-preview.webp?cid=915a99aa5pul1mg99jcokcik1dxjd10x6gq8dprgvqu0pb67&rid=giphy-preview.webp&ct=g")
                )
            ),
            Data(
                "10", Images(
                    Original("https://media2.giphy.com/media"),
                    PreviewWebp("https://media2.giphy.com")
                )
            ),
        )
}