# DelegateRVAdapter
a simple adapter for recyclerView with multi-item

## How to use (in Kotlin)

### Model
create a data class called `Model` 
```kotlin
data class Model(content:String)
```

### ViewHolder
you should create a subclass of BaseVH which represents a single item
```kotlin
class ModelVH(view: View) : BaseVH<Model>(view) {
    override fun onBind(position: Int, items: MutableList<*>, model: Model) {
//      fill your views with model's data here      

    }
}
```

### Delegate
create a subclass of ModelDelegate which can determine which BaseVH should be created
```kotlin
class MyModelDelegate :ModelDelegate<Model>{
    override fun createVH(parent: ViewGroup?, position: Int, items: MutableList<*>): BaseVH<Model> {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_model, parent, false)
        return ModelVH(view)
    }
}
```

### Adapter
when the three step above have been completed , you can create an adapter and bind it with your recyclerView
```kotlin
//...
val items = mutableListOf<Any>()
for(i in 0..100){
    items.add(Model("$i"))
}
val adapter=BaseAdapter(items)
adapter.addHolderDelegate(ModelDelegate())
rv.adapter = adapter
//...
```

## Header and Footer
This adapter treats every part of RecyclerView equally.
If you want to add a header or footer ,just create your data class 、BaseVh and ModelDelegate for theme.


## How to integrate
```groovy
implementation "com.github.xiansenLiu:DelegateRVAdapter:v1.0.7"
```


## License
```
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0
```


