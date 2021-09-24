<?php
    require "connection.php";

    if(!array_key_exists("data", $_POST)){
        echo "Đây là phương thức POST, không thể gọi được như thế này!";
        die();
    }

    $post_data = ($_POST['data']);

    $postObj = json_decode($post_data, true);

    if($postObj['method_name'] == 'method_login'){
        $username = $postObj['username'];
        $password = $postObj['password'];

        $query = "SELECT * FROM sinhvien WHERE `username` = '$username' AND `password` = '$password'";

        $query_result = mysqli_query($connect, $query);

        $count_row = mysqli_num_rows($query_result);

        if($count_row > 0){
            echo "true";
        }else{
            echo "false";
        }

        die();
    }

    if($postObj['method_name'] == 'method_load_user'){

        $query = "SELECT * FROM sinhvien";

        $query_result = mysqli_query($connect, $query);

        //-> array_user -> json -> echo
        
        $array_user = array();
        $array_school = array();
        $array_teacher = array();

        while($data_row = mysqli_fetch_assoc($query_result)){
            
            //data_row -> user
            $user;

            $user['username'] = $data_row['username'];
            $user['password'] = $data_row['password'];
            $user['name'] = $data_row['name'];
            $user['age'] = $data_row['age'];
            $user['birthday'] = $data_row['birthday'];
            $user['address'] = $data_row['address'];
            $user['image'] = $data_row['image'];

            array_push($array_user, $user);
        }

        $objAll;
        $objAll['array_user'] = $array_user;
       
        $json_string = json_encode($objAll);

        echo $json_string;

        die();

    }
?>