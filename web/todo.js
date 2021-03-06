angular.module('todoApp', [])
        .controller('TodoListController', function () {
            var todoList = this;
            todoList.todos = [
                {text: 'Estudiar patrones', done: true},
                {text: 'Avanzar para la entrega 4', done: false}];

            todoList.addTodo = function () {
                todoList.todos.push({
                    text: todoList.todoText, 
                    done: false
                });
                todoList.todoText = '';
            };

            todoList.remaining = function () {
                var count = 0;
                angular.forEach(todoList.todos, function (todo) {
                    count += todo.done ? 0 : 1;
                });
                return count;
            };

            todoList.archive = function () {
                var oldTodos = todoList.todos;
                todoList.todos = [];
                angular.forEach(oldTodos, function (todo) {
                    if (!todo.done){
                        todoList.todos.push(todo);
                    }else{
                        var todoReq  = {
                            todo : todo.text,
                            timestamp : Date.now()
                        };
                        $.post("todo", JSON.stringify(todoReq),
                            function( data ) {
                                console.log(data);
                            }
                        );
                    }
                });
            };
        });