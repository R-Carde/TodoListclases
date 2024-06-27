import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.Tasks.ApiService
import com.example.todolist.Tasks.Task
import com.example.todolist.Tasks.TaskAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ToDoFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var postList: MutableList<Task>
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento para tareas por hacer
        val view = inflater.inflate(R.layout.fragment_todo, container, false)


        // Inicializar las variables después de inflar el diseño
        recyclerView = view.findViewById(R.id.recyclerView)
        postList = mutableListOf()
        taskAdapter = TaskAdapter(postList)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = taskAdapter

        fetchData()

        // Retornar la vista inflada
        return view
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://65bede73dcfcce42a6f2f4ac.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getTasks()
        call.enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                if (response.isSuccessful && response.body() != null) {
                    val allTasks = response.body()!!
                    val tasksToDo = mutableListOf<Task>()
                    val otherTasks = mutableListOf<Task>()

                    // Filtrar las tareas según su categoría
                    for (task in allTasks) {
                        if (task.categoria == "Por hacer") {
                            tasksToDo.add(task)
                        } else {
                            otherTasks.add(task)
                        }
                    }

                    // Actualizar el adaptador con las tareas filtradas
                    postList.clear()
                    postList.addAll(tasksToDo)
                    taskAdapter.notifyDataSetChanged()

                    // Si deseas mostrar las otras tareas en un segundo RecyclerView,
                    // puedes usar otro adaptador y otro RecyclerView para mostrarlas.
                    // recyclerViewOtherTasks.adapter = OtherTaskAdapter(otherTasks)
                } else {
                    // Manejar el error
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                // Manejar la falla
            }
        })
    }

    }








