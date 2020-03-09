function generator(N, L)
    radius = ones(N,1);
    color = ones(N,1);    
    for i = 1:N
        %radius(i) = rand * (L/10);
        radius(i) = 0.1;
    end
    fname = '/Users/prueba/Desktop/SS/Cell-index-method/files/staticFile.txt'
    fid = fopen (fname, "w");
    fprintf (fid, "%d\n", N);
    fprintf (fid, "%d\n", L);
    fprintf(fid, '%f %f\n', [radius, color]');
    fclose (fid);
    
    x = ones(N,1);
    y = ones(N,1);
    collision = 0;
    flag = 1;

    for i = 1:N
        while (flag)
            x(i) = rand * L;
            y(i) = rand * L;
            i
            collision = 0;
        
            if((x(i) < radius) || y(i) < radius || x(i) > L - radius || y(i) > L - radius)
                collision = 1;
            end
            if (i > 1)
                for j = 1:(i-1)
                    distX = x(i) - x(j);
                    distY = y(i) - y(j);
                    dist = sqrt(distX^2 + distY^2);
                   
                    if(dist <= (radius(j) + radius(i)))
                        collision = 1;
                    end
                end
            end
            if (!collision)
                flag = 0;
            end
        end
        flag = 1;
    end
    
    time = 0;

    fname = '/Users/prueba/Desktop/SS/Cell-index-method/files/dynamicFile.txt'
    fid = fopen (fname, "w");
    fprintf(fid, '%d\n', time);
    fprintf(fid, '%f %f\n', [x, y]);
    fclose (fid);

    
end

