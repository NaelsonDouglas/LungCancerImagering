package ic.ufal.br.PDI;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.scijava.module.ModuleInfo;

import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imglib2.IterableInterval;
import net.imglib2.type.numeric.real.DoubleType;
import net.imagej.plugins.commands.typechange.TypeChanger;

public class Main {

	public static void main(String[] args) throws IOException {
		ImageJ ij = new ImageJ();
		ModuleInfo module = ij.command().getCommand(TypeChanger.class);
		
		File file = ij.ui().chooseFile(null, "Open");
		String fileName = file.getName();
		
		PrintWriter pw = new PrintWriter(new File("test.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("magnitude");
		sb.append(",");
		sb.append("label");
		sb.append("\n");
		
		Dataset dataset = ij.scifio().datasetIO().open(file.getPath());
		ij.ui().show(dataset);
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("data", dataset);
		input.put("typeName", "8-bit unsigned integer");
		input.put("combineChannels", true);
		ij.module().waitFor(ij.module().run(module, true, input));
		
		// ij.op().haralick, ij.op().zernike, ij.op().tamura, ij.op().stats estes são os descritores que possuem
		// os atributos
		DoubleType value = ij.op().zernike().magnitude((IterableInterval<DoubleType>)dataset.getImgPlus(), 0,0);
		sb.append(value);
		sb.append(",");
		// maligno ou benigno
		sb.append(fileName.substring(fileName.lastIndexOf(".") - 1, fileName.lastIndexOf(".")));
		sb.append("\n");
		
		pw.write(sb.toString());
        pw.close();
		System.out.println(value);
	}
}
