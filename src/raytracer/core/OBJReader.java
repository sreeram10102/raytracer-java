package raytracer.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import raytracer.core.def.Accelerator;
import raytracer.math.Vec3;

/**
 * Represents a model file reader for the OBJ format
 */
public class OBJReader {

	/**
	 * Reads an OBJ file and uses the given shader for all triangles. While
	 * loading the triangles they are inserted into the given acceleration
	 * structure accelerator.
	 *
	 * @param filename
	 *                    The file to read the data from
	 * @param accelerator
	 *                    The target acceleration structure
	 * @param shader
	 *                    The shader which is used by all triangles
	 * @param scale
	 *                    The scale factor which is responsible for scaling the
	 *                    model
	 * @param translate
	 *                    A vector representing the translation coordinate with
	 *                    which
	 *                    all coordinates have to be translated
	 * @throws IllegalArgumentException
	 *                                  If the filename is null or the empty string,
	 *                                  the accelerator
	 *                                  is null, the shader is null, the translate
	 *                                  vector is null,
	 *                                  the translate vector is not finite or scale
	 *                                  does not
	 *                                  represent a legal (finite) floating point
	 *                                  number
	 */
	public static void read(final String filename,
			final Accelerator accelerator, final Shader shader, final float scale,
			final Vec3 translate) throws FileNotFoundException {
		read(new BufferedInputStream(new FileInputStream(filename)), accelerator, shader, scale, translate);
	}

	/**
	 * Reads an OBJ file and uses the given shader for all triangles. While
	 * loading the triangles they are inserted into the given acceleration
	 * structure accelerator.
	 *
	 * @param in
	 *                    The InputStream of the data to be read.
	 * @param accelerator
	 *                    The target acceleration structure
	 * @param shader
	 *                    The shader which is used by all triangles
	 * @param scale
	 *                    The scale factor which is responsible for scaling the
	 *                    model
	 * @param translate
	 *                    A vector representing the translation coordinate with
	 *                    which
	 *                    all coordinates have to be translated
	 * @throws IllegalArgumentException
	 *                                  If the InputStream is null, the accelerator
	 *                                  is null, the shader is null, the translate
	 *                                  vector is null,
	 *                                  the translate vector is not finite or scale
	 *                                  does not
	 *                                  represent a legal (finite) floating point
	 *                                  number
	 */
	public static void read(final InputStream in,
			final Accelerator accelerator, final Shader shader, final float scale,
			final Vec3 translate) throws FileNotFoundException {
		boolean flag = false;
		Scanner checker = new Scanner(in);
		float a;
		float b;
		float c;
		List<Vec3> newVertex = new ArrayList<>();
		List<Integer> newFaces = new ArrayList<>();
		List<Float> new1 = new ArrayList<>();
		while (checker.hasNext()) {
			while (checker.hasNextLine()) {
				if (checker.nextLine().isEmpty() || checker.nextLine() == "#") {
					checker.skip(checker.nextLine());
				}
			}
			String[] array = checker.nextLine().split("\\s+");
			String[] array2 = new String[1024];
			for (int i = 0; i < array.length; i++) {
				if (array[0].equals('f') && flag == true) {
					array2[i] = array[1];

					array2[i + 1] = array[2];
					array2[i + 2] = array[3];
					checker.nextLine();
					newFaces.add(Integer.parseInt(array[0]));
					newFaces.add(Integer.parseInt(array[1]));
					newFaces.add(Integer.parseInt(array[2]));

				}
				if (array[0].equals('v')) {
					a = Float.parseFloat(array[1]) * scale + translate.x();
					b = Float.parseFloat(array[2]) * scale + translate.y();
					c = Float.parseFloat(array[3]) * scale + translate.z();
					flag = true;
					Vec3 vector = new Vec3(a, b, c);

					newVertex.add(vector);
				}

				new1.get(Integer.parseInt(array2[i]));
				new1.get(Integer.parseInt(array[i + 1]));
				new1.get(Integer.parseInt(array[i + 2]));
			}

		}
	}

}
