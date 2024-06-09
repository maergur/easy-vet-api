package dev.patika.easyvet.core.utilities;

import dev.patika.easyvet.core.result.Result;
import dev.patika.easyvet.core.result.ResultData;
import dev.patika.easyvet.dto.response.CursorResponse;
import dev.patika.easyvet.dto.response.animal.AnimalResponse;
import dev.patika.easyvet.dto.response.doctor.DoctorResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResultHelper {
    public static <T> ResultData<T> created(T data){

        return new ResultData<>(true,Msg.CREATED, "201", data);

    }

    public static <T> ResultData<T> validateError(T data){

        return new ResultData<>(false, Msg.VALIDATE_ERROR,"400",data);

    }
    public static <T> ResultData<T> success(T data){

        return new ResultData<>(true,Msg.OK, "200", data);

    }

    public static <T> ResultData<T> error( String msg){

        return new ResultData<>(false,msg, "200", null);

    }

    public static <T> ResultData<T> duplicateError(T data){

        return new ResultData<>(true,Msg.DUPLICATE_DATA, "400", data);

    }

    public static <T> ResultData<T> availabilityError(T data){

        return new ResultData<>(false,Msg.DUPLICATE_DATA, "400", data);

    }

    public static Result ok(){
        return new Result(true,Msg.OK,"200");
    }
    public static Result notFoundError(String message){

        return new Result(false, message,"404");

    }

    public static <T> ResultData <CursorResponse<T>> cursor(Page<T> pageData) {
        CursorResponse<DoctorResponse> cursor = new CursorResponse<>();
        cursor.setItems((List<DoctorResponse>) pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());
        return ResultHelper.success((CursorResponse<T>) cursor);

    }

}
