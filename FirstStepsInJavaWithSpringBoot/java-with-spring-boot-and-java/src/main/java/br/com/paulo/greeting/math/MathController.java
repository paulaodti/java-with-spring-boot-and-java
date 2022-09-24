package br.com.paulo.greeting.math;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.greeting.exceptions.ResourceNotFoundException;

@RestController()
@RequestMapping(value = "/math")	
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
		{
			throw new ResourceNotFoundException("Os parâmetros passados estão incorretos");
		}
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/diff/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public double diff(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
		{
			throw new ResourceNotFoundException("Os parâmetros passados estão incorretos");
		}
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/prod/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public double prod(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
		{
			throw new ResourceNotFoundException("Os parâmetros passados estão incorretos");
		}
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/quo/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public double quo(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
		{
			throw new ResourceNotFoundException("Os parâmetros passados estão incorretos");
		}
		if (convertToDouble(numberTwo) == 0)
		{
			throw new ResourceNotFoundException("Não é possível fazer uma divisção por zero");
		}
		return convertToDouble(numberOne) / convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/sqrt/{numberOne}",
			method = RequestMethod.GET)
	public double sqrt(
			@PathVariable(value = "numberOne") String numberOne
			) throws Exception {
		if (!isNumeric(numberOne))
		{
			throw new ResourceNotFoundException("Os parâmetros passados estão incorretos");
		}
		return Math.sqrt(convertToDouble(numberOne));
	}

	private double convertToDouble(String strNumber) {
		if (isNumeric(strNumber)) {
			strNumber = strNumber.replaceAll(",", ".");
			return Double.parseDouble(strNumber);
		}
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		strNumber = strNumber.replaceAll(",", ".");
		return strNumber.matches("^-?\\d*\\.?\\d+$");
	}
}
