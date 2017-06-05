package ic.ufal.br.PDI;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.scijava.module.ModuleInfo;

import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imagej.ops.image.cooccurrenceMatrix.MatrixOrientation;
import net.imagej.ops.image.cooccurrenceMatrix.MatrixOrientation2D;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.real.DoubleType;
import net.imagej.plugins.commands.typechange.TypeChanger;

public class Main {

	public static void main(String[] args) throws IOException {
		ImageJ ij = new ImageJ();
		ModuleInfo module = ij.command().getCommand(TypeChanger.class);
		Dataset dataset;
		Map<String, Object> input;

		//File file = ij.ui().chooseFile(null, "Open");
		File imgsPath = new File("nodulo");
		String fileName = "";

		PrintWriter pw = new PrintWriter(new File("test.csv"));
		pw.write("kurtosis,maximum,minimum,mean,geometricMean,standardDeviation,skewness,variance,sum,sumOfSq,haralickContrastV,haralickEntropyV,haralickDiffEntropyV,haralickSumEntropyV,haralickVarianceV,haralickDiffVarianceV,haralickIcm2V,haralickContrastH,haralickEntropyH,haralickDiffEntropyH,haralickSumEntropyH,haralickVarianceH,haralickDiffVarianceH,haralickIcm2H,tamuraCoarseness,zernikeMagnitude,label\n");

		for(File file : imgsPath.listFiles()) {
			StringBuilder sb = new StringBuilder();
			fileName = file.getName();

			dataset = ij.scifio().datasetIO().open(file.getPath());
			//ij.ui().show(dataset);
			input = new HashMap<String, Object>();
			input.put("data", dataset);
			input.put("typeName", "8-bit unsigned integer");
			input.put("combineChannels", true);
			ij.module().waitFor(ij.module().run(module, true, input));

			// ij.op().haralick, ij.op().zernike, ij.op().tamura, ij.op().stats estes são os descritores que possuem
			// os atributos

			// Kurtosis
			DoubleType value = ij.op().stats().kurtosis((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Maximum
			value = ij.op().stats().max((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Minimum
			value = ij.op().stats().min((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Mean
			value = ij.op().stats().mean((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Geometric mean
			value = ij.op().stats().geometricMean((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Standard deviation
			value = ij.op().stats().stdDev((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Skewness
			value = ij.op().stats().skewness((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Variance
			value = ij.op().stats().variance((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Sum
			value = ij.op().stats().sum((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Sum of squares
			value = ij.op().stats().sumOfSquares((IterableInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Vertical contrast:
			value = ij.op().haralick().contrast((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.VERTICAL);
			sb.append(value);
			sb.append(",");

			// Vertical entropy:
			value = ij.op().haralick().entropy((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.VERTICAL);
			sb.append(value);
			sb.append(",");

			// Vertical difference Entropy:
			value = ij.op().haralick().differenceEntropy((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.VERTICAL);
			sb.append(value);
			sb.append(",");

			// Vertical sum entropy:
			value = ij.op().haralick().sumEntropy((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.VERTICAL);
			sb.append(value);
			sb.append(",");

			// Vertical variance:
			value = ij.op().haralick().variance((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.VERTICAL);
			sb.append(value);
			sb.append(",");

			// Vertical difference variance:
			value = ij.op().haralick().differenceVariance((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.VERTICAL);
			sb.append(value);
			sb.append(",");

			// Vertical icm2:
			value = ij.op().haralick().icm2((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.VERTICAL);
			sb.append(value);
			sb.append(",");

			// Horizontal contrast:
			value = ij.op().haralick().contrast((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.HORIZONTAL);
			sb.append(value);
			sb.append(",");

			// Horizontal entropy:
			value = ij.op().haralick().entropy((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.HORIZONTAL);
			sb.append(value);
			sb.append(",");

			// Horizontal difference Entropy:
			value = ij.op().haralick().differenceEntropy((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.HORIZONTAL);
			sb.append(value);
			sb.append(",");

			// Horizontal sum entropy:
			value = ij.op().haralick().sumEntropy((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.HORIZONTAL);
			sb.append(value);
			sb.append(",");

			// Horizontal variance:
			value = ij.op().haralick().variance((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.HORIZONTAL);
			sb.append(value);
			sb.append(",");

			// Horizontal difference variance:
			value = ij.op().haralick().differenceVariance((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.HORIZONTAL);
			sb.append(value);
			sb.append(",");

			// Horizontal icm2:
			value = ij.op().haralick().icm2((IterableInterval<DoubleType>)dataset.getImgPlus(), 256, 1, MatrixOrientation2D.HORIZONTAL);
			sb.append(value);
			sb.append(",");

			// Coarseness:
			value = ij.op().tamura().coarseness((RandomAccessibleInterval<DoubleType>)dataset.getImgPlus());
			sb.append(value);
			sb.append(",");

			// Magnitude:
			value = ij.op().zernike().magnitude((IterableInterval<DoubleType>)dataset.getImgPlus(), 0, 0);
			sb.append(value);
			sb.append(",");

			// Maligno ou benigno:
			sb.append(fileName.substring(fileName.lastIndexOf(".") - 1, fileName.lastIndexOf(".")));
			sb.append("\n");

			pw.write(sb.toString());

			System.out.println(fileName);
		}

		pw.close();
	}
}
