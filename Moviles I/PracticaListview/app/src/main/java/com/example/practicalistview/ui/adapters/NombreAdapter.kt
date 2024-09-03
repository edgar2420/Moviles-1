package com.example.practicalistview.ui.adapters

import android.content.Context
import android.widget.ArrayAdapter

class NombreAdapter(context: Context, objects: Array<out String>) :
    ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, objects)