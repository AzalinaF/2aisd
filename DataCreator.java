import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Random;

public class DataCreator {
    private final int[] sizes = new int[70];               // 70 наборов данных
    private static final String DATA_FILENAME  = "inputData.txt";
    private static final String SIZES_FILENAME = "inputData.sizes";

    /**
     * Сгенерировать данные только если их ещё нет,
     * иначе загрузить ранее сохранённые размеры.
     */
    public void generateFile() {
        File dataFile  = new File(DATA_FILENAME);
        File sizesFile = new File(SIZES_FILENAME);

        // Если оба файла уже есть — просто загружаем массив sizes и выходим
        if (dataFile.exists() && sizesFile.exists()) {
            loadSizes(sizesFile);
            return;
        }

        // Иначе: удаляем старые (на всякий случай) и генерируем заново
        if (dataFile.exists())  dataFile.delete();
        if (sizesFile.exists()) sizesFile.delete();

        Random random = new Random();
        try (FileOutputStream output = new FileOutputStream(dataFile)) {
            for (int i = 0; i < sizes.length; i++) {
                int length = random.nextInt(100, 10000);
                sizes[i] = length;
                for (int j = 0; j < length; j++) {
                    int value = random.nextInt(0, 100000);
                    output.write((value + " ").getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл данных", e);
        }

        // Сохраняем размеры массивов для следующих запусков
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(sizesFile))) {
            for (int length : sizes) {
                dos.writeInt(length);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении размеров", e);
        }
    }

    /** Загрузить ранее сохранённые размеры из бинарного файла */
    private void loadSizes(File sizesFile) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(sizesFile))) {
            for (int i = 0; i < sizes.length; i++) {
                sizes[i] = dis.readInt();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке размеров", e);
        }
    }

    public int[] getSizes() {
        return sizes;
    }
}