# DelegateRVAdapter
a simple adapter for recyclerView with multi-item

## How to use
### Model
create a data class called `Model` 
```kotlin
data class Model(content:String)
```

### ViewHolder
you should create a view holder that represents a single item
```kotlin
class ModelVH(view:View):BaseVH(view){
    override fun onBind(position: Int, items: MutableList<*>){
        //...
    }
}
```

### Delegate
create a subclass of BaseVhDelegate which can determine how to
```kotlin
class ModelDelegate :BaseVHDelegate<Model>{
    override fun createVH(parent: ViewGroup?, position: Int, items: MutableList<*>): BaseVH {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_model, parent, false)
        return ModelVH(view)
    }
}
```

### Adapter
when the three step have been completed , you can create a adapter and bind it to your recyclerView
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


## How to integrate
```groovy
implementation "com.xiansenliu.delegatervadapter:delegatervadapter:1.0.2"
```


