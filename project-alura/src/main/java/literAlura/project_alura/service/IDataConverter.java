package literAlura.project_alura.service;

public interface IDataConverter {

    <T> T getData(String json, Class<T> clase);
}
