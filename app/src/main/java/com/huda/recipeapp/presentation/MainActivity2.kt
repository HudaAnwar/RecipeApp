package com.huda.recipeapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.huda.recipeapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
//<http://sym-json-server.herokuapp.com/posts/4/comments?_page=1&_limit=30>; rel="first",
// <http://sym-json-server.herokuapp.com/posts/4/comments?_page=1&_limit=30>; rel="prev",
// <http://sym-json-server.herokuapp.com/posts/4/comments?_page=2&_limit=30>; rel="last"

//<http://sym-json-server.herokuapp.com/posts/4/comments?_page=1&_limit=30>; rel="first",
// <http://sym-json-server.herokuapp.com/posts/4/comments?_page=2&_limit=30>; rel="next",
// <http://sym-json-server.herokuapp.com/posts/4/comments?_page=2&_limit=30>; rel="last"