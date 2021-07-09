var modelTest;
var ESTILOS_DATA_TEST;

//Inicia la generación del modelo predicitivo
async function runTest() {
	// SET DE DATOS PARA LOS ESTILOS DE APRENDIZAJE
	// Recursos didácticos:
	// Columna 0: Cuaderno de Dibujo
	// Columna 1: Reproductor de sonido
	// Columna 2: Telefono
	// Columna 3: Pelota
	// Columna 4: Puzzle
	// Columna 5: Flores
	// Columna 6: Libro de animaciones
	// Columna 7: Castillo
	// Columna 8: Plastilina
	// Columna 9: Cubos
	// Columna 10: Papel de seda
	// Columna 11: Tambor
	// Estilo Aprendizaje
	// Columna 12: Resultado Estilo Aprendizaje

	ESTILOS_DATA_TEST = [
		// Con Recurso de Columna 0 y 1, respuesta columna 12: 0-LINGÜÍSTICO- VERBAL
		[0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.8, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.7, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.9, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.7, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.8, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		[0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],

		// Con Recurso de Columna 1 y 2, respuesta columna 12: 1-LÓGICA MATEMÁTICA
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
		[0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],

		// Con Recurso de Columna 2 y 3, respuesta columna 12: 2-LÓGICA MATEMÁTICA
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],
		[0, 0, 0.9, 0.8, 0, 0, 0, 0, 0, 0, 0, 0, 2],

		// Con Recurso de Columna 4 y 5, respuesta columna 12: 3-ESPACIAL
		[0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.9, 0.7, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.8, 0.8, 0, 0, 0, 0, 0, 0, 3],
		[0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 0, 3],

		// Con Recurso de Columna 5 y 6, respuesta columna 12: 4-CORPORAL KINESTÉSICA
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.7, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 0, 4],
		[0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 0, 4],

		// Con Recurso de Columna 6 y 7, respuesta columna 12: 5-MUSICAL
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],
		[0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 0, 5],

		// Con Recurso de Columna 7 y 8, respuesta columna 12: 6-INTERPERSONAL
		[0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.7, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 0, 6],
		[0, 0, 0, 0, 0, 0, 0, 0.8, 0.9, 0, 0, 0, 6],

		// Con Recurso de Columna 8 y 9, respuesta columna 12: 7-INTRAPERSONAL
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.8, 0.9, 0, 0, 7],
		[0, 0, 0, 0, 0, 0, 0, 0, 0.9, 0.9, 0, 0, 7],

		// Con Recurso de Columna 3 y 10, respuesta columna 12: 8-NATURALISTA
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],
		[0, 0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0.9, 0, 8],

		];

	const [xTrain, yTrain, xTest, yTest] = getEstilosDataTest(.2);
	entrenarModeloTest(xTrain, yTrain, xTest, yTest);
	console.log('Done Training');

}

//Genera el modelo predictivo
function getEstilosDataTest(testDividir) {
	return tf.tidy(() => {
		const datosPorClase = [];
		const resultadoPorClase = [];	    	        

		$.each(ESTILOS, (index, element) => {
			datosPorClase.push([]);
			resultadoPorClase.push([]);
		});

		for (const fila of ESTILOS_DATA_TEST) {
			const prediccion = fila[fila.length - 1];
			const data = fila.slice(0, fila.length - 1); //
			datosPorClase[prediccion].push(data); //
			resultadoPorClase[prediccion].push(prediccion); //
		}

		const xTrains = [];
		const yTrains = [];
		const xTests = [];
		const yTests = [];

		$.each(ESTILOS, (index, element) => {

			if(datosPorClase[index].length > 0 ){
				const [xTrain, yTrain, xTest, yTest] = 
					convertirATensores(datosPorClase[index], resultadoPorClase[index], testDividir);

				xTrains.push(xTrain);
				yTrains.push(yTrain);
				xTests.push(xTest);
				yTests.push(yTest);
			}

		});	    	     

		const concatAxis = 0;
		const test1 = xTrains;
		const test2 = tf.concat(xTrains, concatAxis);
		console.log('test1 concat', test1);
		console.log('test2 concat', test2);
		return [
			tf.concat(xTrains, concatAxis), tf.concat(yTrains, concatAxis),
			tf.concat(xTests, concatAxis), tf.concat(yTests, concatAxis)
			];

	});
}

//Prueba de predicción
function testPrediccion(val1, val2, val3, val4, val5, val6, val7, val8, val9, val10, val11, val12){
	var arry = [val1, val2, val3, val4, val5, val6, val7, val8, val9, val10, val11, val12];
	console.log('arryTest', arry);
	const input = tf.tensor2d(arry, [1, 12]);
	const prediccion = modelTest.predict(input).dataSync();
	console.log('Prediccion Test', prediccion);
}

//Entrena el modelo predictivo
async function entrenarModeloTest(xTrain, yTrain, xTest, yTest) {
	modelTest = tf.sequential();
	const ratioAprendizaje = .01;
	const numEpochs = 60;
	const optimizer = tf.train.adam(ratioAprendizaje);
	// 16 neuronas en la capa oculta
	modelTest.add(tf.layers.dense({
		units: 16, 
		activation: 'sigmoid', 
		inputShape: [xTrain.shape[1]]
	}));
	// 8 neuronas de salida
	modelTest.add(tf.layers.dense({
		units: 8,
		activation: 'softmax'
	}));

	modelTest.compile({
		optimizer: optimizer,
		loss: 'categoricalCrossentropy',
		metrics: ['accuracy']
	});
}