getData = getDataFunction;
createModel = createModelFunction;
prepareData = prepareDataFunction
trainModel = trainModelFunction;
displayData = displayDataFunction;
evaluateModel = evaluateModelFunction;

async function run() {
  
  // const data = await getData();
  console.log('RUN data:', recursos);
  displayData(recursos);
  
	const model = createModel();  
	tfvis.show.modelSummary({name: 'Modelo de Clasificacion'}, model);

	const tensorData = prepareData(data);
	const {inputs, outputs} = tensorData;
		
	await trainModel(model, inputs, outputs, 100);
	console.log('Done Training');
  
  await evaluateModel(model, inputs, outputs);
}

function loadJSON(path, callback) {   
  var xobj = new XMLHttpRequest();
      xobj.overrideMimeType("application/json");
  xobj.open('GET', path, true);
  xobj.onreadystatechange = function () {
        if (xobj.readyState == 4 && xobj.status == "200") {          
          callback(xobj.responseText);
        }
  };
  xobj.send(null);  
}
/**
  * @desc retrieves data from defined location
  * @return wine data as json
*/
async function getDataFunction() {
  
  loadJSON("recursos.json", (response) => {
    const recursos = JSON.parse(response)
    // const data = await recursos.json();
    console.log('response', data);
    return data;
  });
  // const wineDataReq = await fetch('https://raw.githubusercontent.com/NMZivkovic/file_hosting/master/wine_quality.json');  
  // const wineDataReq = JSON.parse(recursos);
  // console.log('getData', wineDataReq);
  // const wineData = await wineDataReq.json();  
  // return wineData;
}


/**
  * @desc plots one 
  * @param array values - array of values
  * @param string name - name of the plot
  * @param string xoutput - x name 
  * @param string youtput - y name
*/
function singlePlot(values, name, xoutput, youtput)
{
  tfvis.render.scatterplot(
    {name: name},
    {values}, 
    {
      xoutput: xoutput,
      youtput: youtput,
      height: 300,
      xLabel: "Recurso Utilizado",
      yLabel: "Estilo de Aprendizaje"
    }
  );
}

function histograma(values, name, xoutput, youtput) {
  // tfvis.render.barchart(
  //   {name: name}, 
  //   {values},
  //   {
  //    width: 400,
  //    yLabel: 'My value',
  //   }
  // );
  const data = [
    { index: 0, value: 50 },
    { index: 1, value: 100 },
    { index: 2, value: 150 },
   ];
  
  // Render to visor
  const surface = { name: 'Recursos Utilizados', tab: 'Visor' };
  tfvis.render.barchart(surface, values);
}

/**
  * @desc plots one 
  * @param json data - complete json that contains wine quality data 
*/
function displayDataFunction(data){

  // let datos = data.map((index, d) => {
  //   if (index === 0) {
  //     return [
  //       {index: 0, value: d.fixed_acidity},
  //       {index: 1, value: d.volatile_acidity},
  //       {index: 2, value: d.citric_acid},
  //       {index: 3, value: d.residual_sugar},
  //       {index: 4, value: d.chlorides},
  //       {index: 5, value: d.free_sulfur_dioxide},
  //       {index: 6, value: d.total_sulfur_dioxide}
  //     ];
  //   }
  // } 
  // );

  // const data = [
  //   {index: 'foo', value: 1},{index: 'bar', value: 7}, {index: 3, value: 3},
  //   {index: 5, value: 6}];
  // tfvis.render.barchart(document.getElementById('plot5'), data, {
  //   yLabel: 'My value',
  //   width: 400
  // });
  
  // histograma(1, 1, 1, 1);
  const r = [];
  console.log('Display datos', data);
  // data.forEach((i, e) => {
  //   r.push({
  //     index: i,
  //     value: e[0].recurso1
  //   });
  //   console.log(e[0].resurso1);
  // });
  // const d = [];
  // d = data[0];
  // const arregloData = [];
  // data.array.forEach(element => {
    // d.foreach( (index, element) => {
    //   console.log(element);
    // });
    
  // });
  // arregloData.push(data[0])
  // histograma(r, 1, 1, 1);

  let displayData = data.map(d => ({
    x: d.recurso1,
    y: d.estilo
  }));

  singlePlot(displayData, 'Recurso 1 v Estilo', 'Recursos', 'Estilo')

  displayData = data.map(d => ({
    x: d.recurso2,
    y: d.quality
  }));

  singlePlot(displayData, 'Recurso 2 v Estilo', 'Recursos', 'Estilo')

  displayData = data.map(d => ({
    x: d.recurso3,
    y: d.estilo
  }));

  singlePlot(displayData, 'Recurso 3 v Estilo', 'Recursos', 'Estilo')
}

/**
  * @desc creates tensorflow graph
  * @return model
*/
function createModelFunction() {
  const model = tf.sequential(); 
  model.add(tf.layers.dense({inputShape: [6], units: 50, useBias: true, activation: 'sigmoid'}));
  model.add(tf.layers.dense({units: 30, useBias: true, activation: 'sigmoid'}));
  model.add(tf.layers.dense({units: 20, useBias: true, activation: 'sigmoid'}));

  return model;
}

/**
  * @desc creates array of input data for every sample
  * @param json data - complete json that contains wine quality data 
  * @return array of input data
*/
function extractInputs(data)
{
  let inputs = []
  inputs = data.map(d => [d.fixed_acidity, d.volatile_acidity, d.citric_acid, d.residual_sugar, d.chlorides, d.free_sulfur_dioxide, d.total_sulfur_dioxide, d.density, d.pH, d.sulphates, d.alcohol])
	return inputs;
}

/**
  * @desc converts data from json format to tensors
  * @param json data - complete json that contains wine quality data 
  * @return tuple of converted data that can be used for training model
*/
function prepareDataFunction(data) {
  
  return tf.tidy(() => {
    tf.util.shuffle(data);
    
    const inputs = extractInputs(data);
    const outputs = data.map(d => d.quality);

    const inputTensor = tf.tensor2d(inputs, [inputs.length, inputs[0].length]);
    const outputTensor = tf.oneHot(tf.tensor1d(outputs, 'int32'), 10);

    const inputMax = inputTensor.max();
    const inputMin = inputTensor.min();  
    const outputMax = outputTensor.max();
    const outputMin = outputTensor.min();

    const normalizedInputs = inputTensor.sub(inputMin).div(inputMax.sub(inputMin));
    const normalizedoutputs = outputTensor.sub(outputMin).div(outputMax.sub(outputMin));

    return {
      inputs: normalizedInputs,
      outputs: normalizedoutputs,
      inputMax,
      inputMin,
      outputMax,
      outputMin,
    }
  });  
}

/**
  * @desc trains model
  * @return trained model
*/
async function trainModelFunction(model, inputs, outputs, epochs) {
  model.compile({
    optimizer: tf.train.adam(),
    loss: 'categoricalCrossentropy',
    metrics: ['accuracy'],
  });
  
  const batchSize = 64;
  
  return await model.fit(inputs, outputs, {
    batchSize,
    epochs,
    shuffle: true,
    callbacks: tfvis.show.fitCallbacks(
      { name: 'Training Performance' },
      ['loss', 'accuracy'], 
      { height: 200, callbacks: ['onEpochEnd'] }
    )
  });
}

/**
  * @desc evaluates the model
*/
async function evaluateModelFunction(model, inputs, outputs)
{
  const result = await model.evaluate(inputs, outputs, {batchSize: 64});
  console.log('Accuracy is:')
  result[1].print();
}

//document.addEventListener('DOMContentLoaded', run);

const recursos = [];
recursos.push({
		"recurso1": 0.25,
		"recurso2": 0.6,
		"recurso3": 0.9,
		"recurso4": 0.85,
		"recurso5": 0.44,
    "recurso6": 0.15,
    "estilo": 1
	}, {
		"recurso1": 0.25,
		"recurso2": 0.3,
		"recurso3": 0.5,
		"recurso4": 0.55,
		"recurso5": 0.24,
    "recurso6": 0.55,
    "estilo": 1
	}, {
		"recurso1": 0.95,
		"recurso2": 0.3,
		"recurso3": 0.6,
		"recurso4": 0.85,
		"recurso5": 0.54,
    "recurso6": 0.65,
    "estilo": 2
	}, {
		"recurso1": 0.15,
		"recurso2": 0.2,
		"recurso3": 0.7,
		"recurso4": 0.55,
		"recurso5": 0.25,
    "recurso6": 0.85,
    "estilo": 2
	}, {
		"recurso1": 0.55,
		"recurso2": 0.15,
		"recurso3": 0.9,
		"recurso4": 0.4,
		"recurso5": 0.5,
    "recurso6": 0.7,
    "estilo": 3
	}, {
		"recurso1": 0.25,
		"recurso2": 0.4,
		"recurso3": 0.9,
		"recurso4": 0.35,
		"recurso5": 0.65,
    "recurso6": 0.95,
    "estilo": 3
	}, {
		"recurso1": 0.2,
		"recurso2": 0.8,
		"recurso3": 0.25,
		"recurso4": 0.85,
		"recurso5": 0.8,
    "recurso6": 0.35,
    "estilo": 3
	});
